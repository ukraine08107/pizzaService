package whz.pti.eva.pizza_service_g11.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whz.pti.eva.pizza_service_g11.cart.domain.Cart;
import whz.pti.eva.pizza_service_g11.cart.domain.CartRepository;

@Service
public class CartServiceImpl implements CartService {
	
	private CartRepository cartRepository;
	
	@Autowired
	public CartServiceImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Override
	public void createCart(Cart cart) {
		cartRepository.save(cart);
	}

	@Override
	public Cart getCartByUserId(String userId) {
		Cart cart = cartRepository.getCartByIdOfUser(userId);
		return cart;
	}


	@Override
	public void delete(Cart cart) {
		cartRepository.delete(cart);
	}
}
