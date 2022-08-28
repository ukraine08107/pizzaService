package whz.pti.eva.pizza_service_g11.pizza.domain;

import whz.pti.eva.pizza_service_g11.common.BaseEntity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Pizza extends BaseEntity<Long> {

    private String name;
    private BigDecimal priceLarge;
    private BigDecimal priceMedium;
    private BigDecimal priceSmall;
    private String imagePath;

    public void setImagePath(String path) {
        this.imagePath = path;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Pizza() {
    }

    public Pizza(String name, BigDecimal priceLarge, BigDecimal priceMedium, BigDecimal priceSmall) {
        super();
        this.name = name;
        this.priceLarge = priceLarge;
        this.priceMedium = priceMedium;
        this.priceSmall = priceSmall;
    }


    public String getName() {
        return name;
    }

    public BigDecimal getPriceLarge() {
        return priceLarge;
    }

    public BigDecimal getPriceMedium() {
        return priceMedium;
    }

    public BigDecimal getPriceSmall() {
        return priceSmall;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriceLarge(BigDecimal priceLarge) {
        this.priceLarge = priceLarge;
    }

    public void setPriceMedium(BigDecimal priceMedium) {
        this.priceMedium = priceMedium;
    }

    public void setPriceSmall(BigDecimal priceSmall) {
        this.priceSmall = priceSmall;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    public PizzaSize getChosenSize(String size) {
        if (size.equals("small")) {
            return PizzaSize.SMALL;
        } else if (size.equals("medium")) {
            return PizzaSize.MEDIUM;
        } else if (size.equals("large")) {
            return PizzaSize.LARGE;
        }
        return null;
    }

    public BigDecimal getChosenPrice(String size) {
        size = size.toLowerCase();
        switch (size) {
            case "small":
                return priceSmall;
            case "medium":
                return priceMedium;
            case "large":
                return priceLarge;
        }
        return null;
    }

}
