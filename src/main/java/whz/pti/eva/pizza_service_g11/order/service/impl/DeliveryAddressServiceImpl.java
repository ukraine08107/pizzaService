package whz.pti.eva.pizza_service_g11.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import whz.pti.eva.pizza_service_g11.order.domain.DeliveryAddress;
import whz.pti.eva.pizza_service_g11.order.domain.repositories.DeliveryAddressRepository;
import whz.pti.eva.pizza_service_g11.order.service.DeliveryAddressService;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

	private DeliveryAddressRepository deliveryAddressRepository;
	
	
	@Autowired
	public DeliveryAddressServiceImpl(DeliveryAddressRepository deliveryAddressRepository) {
		this.deliveryAddressRepository = deliveryAddressRepository;
	}



	@Override
	public void createDeliveryAddress(DeliveryAddress deliveryAddress) {
		deliveryAddressRepository.save(deliveryAddress);
	}

}
