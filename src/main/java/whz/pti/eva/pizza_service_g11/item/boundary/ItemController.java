package whz.pti.eva.pizza_service_g11.item.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.eva.pizza_service_g11.cart.domain.Cart;
import whz.pti.eva.pizza_service_g11.cart.service.CartService;
import whz.pti.eva.pizza_service_g11.item.domain.Item;
import whz.pti.eva.pizza_service_g11.item.service.ItemService;
import whz.pti.eva.pizza_service_g11.pizza.domain.Pizza;
import whz.pti.eva.pizza_service_g11.pizza.service.PizzaService;
import whz.pti.eva.pizza_service_g11.security.boundary.CurrentUserControllerAdvice;

@Controller
public class ItemController {

    @Autowired
    PizzaService pizzaService;

    @Autowired
    CartService cartService;

    @Autowired
    ItemService itemService;

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);


    @PostMapping(value = "/add")
    public String addItemToCart(@RequestParam int quantity, @RequestParam Long id, @RequestParam String size, Model model) {
        log.info("ItemController. PizzaId: " + id + ", Size: " + size + ", Quantity " + quantity);
        String userId = CurrentUserControllerAdvice.getCurrentUserId().toString();


        Cart cart = cartService.getCartByUserId(userId);

        if (cart == null) {
            cart = new Cart(0, userId);
            cartService.createCart(cart);
        }
        Item item = new Item();
        item.setQuantity(quantity);
        Pizza pizza = pizzaService.getPizzaById(id);
        item.setPizza(pizza);
        item.setItemId(id.toString());
        item.setName(pizza.getName());

        if (pizza.getChosenSize(size) != null) {
            item.setPizzaSize(pizza.getChosenSize(size));
        }
        for (Item cartItem : cart.getItems()) {
            if (cartItem.getPizza().getId().equals(id)
                    && cartItem.getPizzaSize().toString().equalsIgnoreCase(size)) {
                cartItem.addQuantityToExistingItem(quantity);
                itemService.createItem(cartItem);
                cartService.createCart(cart);
                return "redirect:main";
            }
        }
        cart.setItem(item);
        item.setCart(cart);

        cartService.createCart(cart);

        log.info("ItemController: " + item.toString());
        log.info("ItemController: " + cart.toString());


        model.addAttribute("message", "Product added to cart succesfully");


        return "redirect:main";
    }

}
