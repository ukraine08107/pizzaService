package whz.pti.eva.pizza_service_g11.cart.domain;

import whz.pti.eva.pizza_service_g11.common.BaseEntity;
import whz.pti.eva.pizza_service_g11.item.domain.Item;
import whz.pti.eva.pizza_service_g11.security.domain.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart extends BaseEntity<Long> {

    private int quantity;
    private String idOfUser;
    private BigDecimal cartTotal;


    public BigDecimal getCartTotal() {
        for (Item item : items) {
            this.cartTotal = cartTotal
                    .add(
                            item.getPizza().getChosenPrice(item.getPizzaSize().toString())
                                    .multiply(
                                            new BigDecimal(item.getQuantity())
                                    ));
        }
        return cartTotal;
    }

    public void setCartTotal(BigDecimal cartTotal) {
        this.cartTotal = cartTotal;
    }


    public int getAmountOfAllCartItems() {
        for (Item item : items) {
            this.quantity = quantity + item.getQuantity();
        }
        return this.quantity;
    }

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Item> items;

    @OneToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart() {
        this.items = new ArrayList<>();
        this.cartTotal = new BigDecimal(0);
    }

    public Cart(int quantity, String idOfUser) {
        super();
        this.cartTotal = new BigDecimal(0);
        this.items = new ArrayList<>();
        this.quantity = quantity;
        this.idOfUser = idOfUser;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getIdOfUser() {
        return idOfUser;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIdOfUser(String idOfUser) {
        this.idOfUser = idOfUser;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setItem(Item item) {
        this.items.add(item);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + getId() +
                ", quantity=" + quantity +
                ", idOfUser='" + idOfUser + '\'' +
                '}';
    }
}
