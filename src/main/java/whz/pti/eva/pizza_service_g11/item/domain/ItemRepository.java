package whz.pti.eva.pizza_service_g11.item.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.pti.eva.pizza_service_g11.cart.domain.Cart;


public interface ItemRepository  extends JpaRepository<Item, Long> {

    Item getItemByCart(Cart cart);

    void deleteItemById(Long id);

    void deleteAllByCart(Cart cart);
}
