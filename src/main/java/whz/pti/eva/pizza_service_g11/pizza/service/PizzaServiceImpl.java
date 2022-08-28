package whz.pti.eva.pizza_service_g11.pizza.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whz.pti.eva.pizza_service_g11.pizza.domain.Pizza;
import whz.pti.eva.pizza_service_g11.pizza.domain.PizzaRepository;

@Service
public class PizzaServiceImpl implements PizzaService {
	
	private PizzaRepository pizzaRepository;
	
	@Autowired
	public PizzaServiceImpl(PizzaRepository pizzaRepository) {
		this.pizzaRepository = pizzaRepository;
	}

	@Override
	public void addPizza(Pizza pizza){
			pizzaRepository.save(pizza);
	}

	@Override
	public List<Pizza> listAllPizzas() {
		List<Pizza> pizzas = new ArrayList<>();
		pizzas = pizzaRepository.findAll();
		return pizzas;
	}

	@Override
	public Pizza getPizzaById(Long id) {
		return pizzaRepository.getOne(id);
	}
}
