package whz.pti.eva.pizza_service_g11.item.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import whz.pti.eva.pizza_service_g11.cart.domain.Cart;
import whz.pti.eva.pizza_service_g11.item.domain.Item;
import whz.pti.eva.pizza_service_g11.item.domain.ItemRepository;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
	
	private ItemRepository itemRepository;
	
	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}




	@Override
	public void createItem(Item item) {
		itemRepository.save(item);
	}

	@Override
	public Item getItemByCart(Cart cart) {
		Item item = itemRepository.getItemByCart(cart);
		return item;
	}

	public void deleteItemById(Long id) {
		itemRepository.deleteItemById(id);
	}

	@Override
	public void deleteAllByCart(Cart cart) {
		itemRepository.deleteAllByCart(cart);
	}


	@Override
	public void deleteAll(List<Item> items) {
			itemRepository.deleteAll();
	}
}
