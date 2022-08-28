package whz.pti.eva.pizza_service_g11.cart.service;

import whz.pti.eva.pizza_service_g11.cart.domain.Cart;

public interface CartService {
	void createCart(Cart cart);
	Cart getCartByUserId(String userId);
	void delete(Cart cart);
}
