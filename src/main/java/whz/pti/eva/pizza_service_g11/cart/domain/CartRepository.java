package whz.pti.eva.pizza_service_g11.cart.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart getCartByIdOfUser(String userId);

}
