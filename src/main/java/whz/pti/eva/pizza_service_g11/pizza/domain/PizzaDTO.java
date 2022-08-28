package whz.pti.eva.pizza_service_g11.pizza.domain;

import java.math.BigDecimal;

public class PizzaDTO {
    private int quantity;
    private String itemId;
    private String size;
    private String name;
    private BigDecimal totalPrice;

    public PizzaDTO(int quantity, Pizza pizza, String size) {
        this.quantity = quantity;
        this.itemId = pizza.getId().toString();
        this.size = size;
        this.name = pizza.getName();
        totalPrice = pizza.getChosenPrice(size).multiply(new BigDecimal(quantity));
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }


    @Override
    public String toString() {
        return "PizzaDTO{" +
                "quantity=" + quantity +
                ", itemId='" + itemId + '\'' +
                ", size='" + size + '\'' +
                ", name='" + name + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
