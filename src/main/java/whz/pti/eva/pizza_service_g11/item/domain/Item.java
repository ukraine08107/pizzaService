package whz.pti.eva.pizza_service_g11.item.domain;

import whz.pti.eva.pizza_service_g11.cart.domain.Cart;
import whz.pti.eva.pizza_service_g11.common.BaseEntity;
import whz.pti.eva.pizza_service_g11.pizza.domain.Pizza;
import whz.pti.eva.pizza_service_g11.pizza.domain.PizzaSize;

import javax.persistence.*;

@Entity
public class Item extends BaseEntity<Long> {

    private int quantity;
    private String itemId;
    private String name;

    @ManyToOne
    private Cart cart;

    @OneToOne
    private Pizza pizza;

    @Column(name = "pizzaSize", nullable = false)
    private PizzaSize pizzaSize;

    public Item() {
        this.pizza = new Pizza();
        this.cart = new Cart();
    }

    public Item(int quantity, String itemId, PizzaSize pizzaSize) {
        super();
        this.quantity = quantity;
        this.itemId = itemId;
        this.pizzaSize = pizzaSize;
        this.pizza = new Pizza();
        this.cart = new Cart();
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", quantity=" + quantity +
                ", itemId='" + itemId + '\'' +
                ", pizzaSize=" + pizzaSize.toString() +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public void setPizzaSize(PizzaSize pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public PizzaSize getPizzaSize() {
        return pizzaSize;
    }
    
    public void addQuantityToExistingItem(int quantity) {
    	this.quantity += quantity;
    }
}
