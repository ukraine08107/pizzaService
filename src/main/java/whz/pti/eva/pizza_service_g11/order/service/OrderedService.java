package whz.pti.eva.pizza_service_g11.order.service;

import whz.pti.eva.pizza_service_g11.order.domain.Ordered;

import java.util.List;

public interface OrderedService {
	void createOrdered(Ordered ordered);
	Ordered getOrderedByUserId(String userId);

	List<Ordered> getOrderedsByUserId(String userId);
}
