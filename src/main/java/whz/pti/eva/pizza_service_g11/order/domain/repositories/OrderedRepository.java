package whz.pti.eva.pizza_service_g11.order.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import whz.pti.eva.pizza_service_g11.order.domain.Ordered;

import java.util.List;

public interface OrderedRepository extends JpaRepository<Ordered, Long> {

    Ordered getOrderedByUserId(String userId);

    List<Ordered> getAllByUserId(String userId);

}
