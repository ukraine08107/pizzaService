package whz.pti.eva.pizza_service_g11.order.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import whz.pti.eva.pizza_service_g11.order.domain.Ordered;
import whz.pti.eva.pizza_service_g11.order.domain.repositories.OrderedRepository;
import whz.pti.eva.pizza_service_g11.order.service.OrderedService;

import java.util.List;

@Service
public class OrderedServiceImpl implements OrderedService {

	private OrderedRepository orderedRepository;
	
	@Autowired
	public OrderedServiceImpl(OrderedRepository orderedRepository) {
		super();
		this.orderedRepository = orderedRepository;
	}


	@Override
	public void createOrdered(Ordered ordered) {
		orderedRepository.save(ordered);
	}

	@Override
	public Ordered getOrderedByUserId(String userId) {
		return orderedRepository.getOrderedByUserId(userId);
	}


	@Override
	public List<Ordered> getOrderedsByUserId(String userId) {
		return orderedRepository.getAllByUserId(userId);
	}
}
