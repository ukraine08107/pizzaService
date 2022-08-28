package whz.pti.eva.pizza_service_g11.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whz.pti.eva.pizza_service_g11.order.domain.OrderedItem;
import whz.pti.eva.pizza_service_g11.order.domain.repositories.OrderedItemRepository;
import whz.pti.eva.pizza_service_g11.order.service.OrderedItemService;

import java.util.List;

@Service
public class OrderedItemServiceImpl implements OrderedItemService {
	
	private OrderedItemRepository orderedItemRepository;

	@Autowired
	public OrderedItemServiceImpl(OrderedItemRepository orderedItemRepository) {
		super();
		this.orderedItemRepository = orderedItemRepository;
	}




	@Override
	public void createOrderedItem(OrderedItem orderedItem) {
		orderedItemRepository.save(orderedItem);
	}

	@Override
	public void createOrderedItems(List<OrderedItem> orderedItems) {
		for (OrderedItem orderedItem: orderedItems){
			orderedItemRepository.save(orderedItem);
		}
	}
}
