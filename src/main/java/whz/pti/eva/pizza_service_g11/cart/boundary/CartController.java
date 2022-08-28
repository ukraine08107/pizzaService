package whz.pti.eva.pizza_service_g11.cart.boundary;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import whz.pti.eva.pizza_service_g11.cart.domain.Cart;
import whz.pti.eva.pizza_service_g11.cart.service.CartService;
import whz.pti.eva.pizza_service_g11.item.domain.Item;
import whz.pti.eva.pizza_service_g11.item.service.ItemService;
import whz.pti.eva.pizza_service_g11.pizza.domain.Pizza;
import whz.pti.eva.pizza_service_g11.pizza.domain.PizzaDTO;
import whz.pti.eva.pizza_service_g11.security.boundary.CurrentUserControllerAdvice;
import whz.pti.eva.pizza_service_g11.security.service.currentuser.CurrentUserDetailsService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {


    private static final Logger log = LoggerFactory.getLogger(CartController.class);

    @Autowired
    CartService cartService;

    @Autowired
    ItemService itemService;

    @Autowired
    CurrentUserDetailsService currentUserDetailsService;

    @GetMapping("/myCart")
    public String myCart(Model model) {

        Cart cart = cartService.getCartByUserId(CurrentUserControllerAdvice.getCurrentUserId().toString());

        if (cart == null || cart.getItems() == null) {
            model.addAttribute("error", "Cart is empty!");
            return "myCart";
        }

        List<Item> items = cart.getItems();
        List<Pizza> pizzas = new ArrayList<>();
        List<PizzaDTO> pizzaDTOList = new ArrayList<>();

        for (Item item : items) {
            pizzas.add(item.getPizza());
            pizzaDTOList.add(new PizzaDTO(item.getQuantity(), item.getPizza(), item.getPizzaSize().toString().toLowerCase()));
        }

        model.addAttribute("myItems", pizzaDTOList);
        model.addAttribute("totalItems", cart.getAmountOfAllCartItems());
        model.addAttribute("totalMoney", cart.getCartTotal());
        model.addAttribute("added", true);

        return "myCart";
    }

//    @GetMapping(value = "/delete/{id}")
//    public String deleteFromCart(@PathVariable("id") String itemId, Model model) {
//
//        String userId = CurrentUserControllerAdvice.getCurrentUserId().toString();
//        itemService.deleteItemById(Long.parseLong(itemId));
//
//        Cart cart = cartService.getCartByUserId(userId);
//        for (Item item: cart.getItems()){
//            log.info(item.toString());
//        }
//        List<PizzaDTO> pizzaDTOList = new ArrayList<>();
//
//        if (cart != null) {
//            for (Item cartItem : cart.getItems()) {
//                pizzaDTOList.add(new PizzaDTO(cartItem.getQuantity(), cartItem.getPizza(), cartItem.getPizzaSize().toString().toLowerCase()));
//            }
//        }
//        model.addAttribute("myItems", pizzaDTOList);
//        return "myCart";
//    }


    @GetMapping("/checkout")
    public String goToCheckout(Model model) {
        String userId = CurrentUserControllerAdvice.getCurrentUserId().toString();
        Cart cart = cartService.getCartByUserId(userId);
        model.addAttribute("totalItems", cart.getAmountOfAllCartItems());
        model.addAttribute("totalMoney", cart.getCartTotal());
        log.info("tostring:"+cart.toString());

        return "checkout";
    }

    @RequestMapping(path = "/delete")
    public String removeFromCart(Model model) {

        String userId = CurrentUserControllerAdvice.getCurrentUserId().toString();
        Cart cart = cartService.getCartByUserId(userId);

        cartService.delete(cart);


        return "redirect:/myCart";
    }
}
