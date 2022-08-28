package whz.pti.eva.pizza_service_g11.order.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import whz.pti.eva.pizza_service_g11.order.domain.DeliveryAddress;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

}
