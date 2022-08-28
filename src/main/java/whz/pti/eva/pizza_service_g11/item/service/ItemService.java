package whz.pti.eva.pizza_service_g11.item.service;

import whz.pti.eva.pizza_service_g11.cart.domain.Cart;
import whz.pti.eva.pizza_service_g11.item.domain.Item;

import java.util.List;

public interface  ItemService {
	void createItem(Item item);

    Item getItemByCart(Cart cart);

    void deleteItemById(Long id);

    void deleteAll(List<Item> items);

    void deleteAllByCart(Cart cart);
}
