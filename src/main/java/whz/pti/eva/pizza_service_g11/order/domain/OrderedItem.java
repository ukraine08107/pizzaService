package whz.pti.eva.pizza_service_g11.order.domain;

import whz.pti.eva.pizza_service_g11.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class OrderedItem extends BaseEntity<Long> {
	
	private long pizzaId;
	private String name; 
	private int quantity;
	private String userId;
	@ManyToOne
	private Ordered ordered;

	public OrderedItem() {
	}

	public OrderedItem(long pizzaId, String name, int quantity, String userId) {
		super();
		this.pizzaId = pizzaId;
		this.name = name;
		this.quantity = quantity;
		this.userId = userId;
	}
	
	public long getPizzaId() {
		return pizzaId;
	}
	public String getName() {
		return name;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getUserId() {
		return userId;
	}
	public void setPizzaId(long pizzaId) {
		this.pizzaId = pizzaId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Ordered getOrdered() {
		return ordered;
	}

	public void setOrdered(Ordered ordered) {
		this.ordered = ordered;
	}
}

