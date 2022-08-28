package whz.pti.eva.pizza_service_g11.order.service;

import whz.pti.eva.pizza_service_g11.order.domain.Ordered;
import whz.pti.eva.pizza_service_g11.order.domain.OrderedItem;

import java.util.List;


public interface OrderedItemService {
	void createOrderedItem(OrderedItem orderedItem);
	void createOrderedItems(List<OrderedItem> orderedItems);

}
