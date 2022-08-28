package whz.pti.eva.pizza_service_g11.order.domain.dto;

import whz.pti.eva.pizza_service_g11.order.domain.OrderedItem;

import java.math.BigDecimal;
import java.util.List;

public class OrderedDTO {
    private String address;
    private int totalMoney;
    private List<OrderedItem> orderedItems;
    private int orderNumber;

    public OrderedDTO(int orderedNumber, String address, int totalMoney, List<OrderedItem> orderedItems) {
        this.address = address;
        this.totalMoney = totalMoney;
        this.orderedItems = orderedItems;
        this.orderNumber = orderedNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

}
