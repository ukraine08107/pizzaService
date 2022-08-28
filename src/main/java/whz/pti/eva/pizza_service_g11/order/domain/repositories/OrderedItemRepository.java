package whz.pti.eva.pizza_service_g11.order.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import whz.pti.eva.pizza_service_g11.order.domain.OrderedItem;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {

}
