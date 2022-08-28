package whz.pti.eva.pizza_service_g11.pizza.service;

import java.util.List;

import whz.pti.eva.pizza_service_g11.pizza.domain.Pizza;

public interface PizzaService {
	
	public List<Pizza> listAllPizzas();
	
	public void addPizza(Pizza pizza);

	public Pizza getPizzaById(Long id);
}
