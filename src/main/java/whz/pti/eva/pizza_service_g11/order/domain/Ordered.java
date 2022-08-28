package whz.pti.eva.pizza_service_g11.order.domain;

import whz.pti.eva.pizza_service_g11.common.BaseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Ordered extends BaseEntity<Long> {
	
	private String userId;
	private int numberOfItems;
	private int orderTotal;
	
	@OneToMany(mappedBy= "ordered", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<OrderedItem> items;

	@ManyToOne
	private DeliveryAddress deliveryAddress;

	public Ordered() {
	}

	public DeliveryAddress getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Ordered(String userId) {
		super();
		items = new ArrayList<>();
		this.userId = userId;
		this.numberOfItems = 0;
	}

	@Override
	public Long getId() {
		return super.getId();
	}

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getUserId() {
		return userId;
	}


	public int getNumberOfItems() {
		return numberOfItems;
	}


	public List<OrderedItem> getItems() {
		return items;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}


	public void setItems(List<OrderedItem> items) {
		this.items = items;
		setNumberOfItems(this.items.size());
	}

	public void addItems(List<OrderedItem> items){
		this.items.addAll(items);
		setNumberOfItems(this.items.size());
	}
	public void addItem(OrderedItem item){
		this.items.add(item);
		setNumberOfItems(this.items.size());
	}

	
}
