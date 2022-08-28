package whz.pti.eva.pizza_service_g11.payment.boundary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import whz.pti.eva.pizza_service_g11.cart.domain.Cart;
import whz.pti.eva.pizza_service_g11.cart.service.CartService;
import whz.pti.eva.pizza_service_g11.item.domain.Item;
import whz.pti.eva.pizza_service_g11.item.service.ItemService;
import whz.pti.eva.pizza_service_g11.order.domain.DeliveryAddress;
import whz.pti.eva.pizza_service_g11.order.domain.Ordered;
import whz.pti.eva.pizza_service_g11.order.domain.OrderedItem;
import whz.pti.eva.pizza_service_g11.order.service.DeliveryAddressService;
import whz.pti.eva.pizza_service_g11.order.service.OrderedItemService;
import whz.pti.eva.pizza_service_g11.order.service.OrderedService;
import whz.pti.eva.pizza_service_g11.payment.service.PaymentService;
import whz.pti.eva.pizza_service_g11.security.boundary.CurrentUserControllerAdvice;
import whz.pti.eva.pizza_service_g11.security.domain.User;
import whz.pti.eva.pizza_service_g11.security.service.user.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Controller
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderedService orderedService;
    @Autowired
    OrderedItemService orderedItemService;

    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @Autowired
    DeliveryAddressService deliveryAddressService;


    @Transactional
    @PostMapping("/pay")
    public String doPayAction(@ModelAttribute("address") DeliveryAddress deliveryAddress, Model model) {

        int totalMoney = 0;

        String from = CurrentUserControllerAdvice.getCurrentUserId().toString();

        Cart cart = cartService.getCartByUserId(from);
        //setting address to the user and creating address
        User user = userService.getUserById(Long.valueOf(from));
        user.setAddress(deliveryAddress);
        deliveryAddressService.createDeliveryAddress(deliveryAddress);
        userService.createUser(user);

        //getting balance of user from mobilepayment application
        int balance = Integer.valueOf(paymentService.checkBalance(from).getCode().replaceAll("Kontostand betraegt ", ""));
        totalMoney = cart.getCartTotal().intValue();

        //checking is the balance enough to pay for
        //if balance enough we pay
        //if not we return page with message
        if (balance > totalMoney) {
            paymentService.doPayAction(from, totalMoney);
        } else {
            model.addAttribute("message", "Payment transaction is not successfull. " +
                    "You don't have enough money! Order is: " + totalMoney + " euro. But your balance is: " + balance + " euro :(");
            return "ordered";
        }

        //get ordered class for user
        Ordered ordered = new Ordered(from);
        ordered.setOrderTotal(totalMoney);
        ordered.setDeliveryAddress(deliveryAddress);
        List<OrderedItem> orderedItems = new ArrayList<>();
        //creating new ordereditems with items, that are ordered and payed
        for (int i = 0; i < cart.getItems().size(); i++) {
            OrderedItem orderedItem = new OrderedItem();
            flipItemToOrderedItem(cart.getItems().get(i), orderedItem);
            orderedItem.setUserId(from);
            orderedItem.setOrdered(ordered);

            orderedItems.add(orderedItem);
        }

        ordered.setItems(orderedItems);
        orderedService.createOrdered(ordered);
        orderedItemService.createOrderedItems(orderedItems);

        //deleting items after ordering
        itemService.deleteAll(cart.getItems());
        //deleting cart after ordering
        cartService.delete(cart);

        model.addAttribute("message", "Payment transaction is successfull");

        return "ordered";
    }


    void flipItemToOrderedItem(Item item, OrderedItem orderedItem) {
        orderedItem.setName(item.getName());
        orderedItem.setPizzaId(Long.valueOf(item.getItemId()));
        orderedItem.setQuantity(item.getQuantity());
    }
}
