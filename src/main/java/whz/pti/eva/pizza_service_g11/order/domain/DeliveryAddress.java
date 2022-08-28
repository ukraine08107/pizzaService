package whz.pti.eva.pizza_service_g11.order.domain;

import whz.pti.eva.pizza_service_g11.common.BaseEntity;
import whz.pti.eva.pizza_service_g11.security.domain.User;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class DeliveryAddress extends BaseEntity<Long> {

	private String street;
	private String houseNumber;
	private String town;
	private String postalCode;
	
	@ManyToMany
	private List<User> users;

	public List<Ordered> getOrdereds() {
		return ordereds;
	}

	public void setOrdereds(List<Ordered> ordereds) {
		this.ordereds = ordereds;
	}

	@OneToMany(mappedBy= "deliveryAddress", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Ordered> ordereds;
	
	public DeliveryAddress(String street, String houseNumber, String town, String postalCode) {
		super();
		this.users = new ArrayList<>();
		this.street = street;
		this.houseNumber = houseNumber;
		this.town = town;
		this.postalCode = postalCode;
	}

	public DeliveryAddress(){}
	
	public List<User> getUsers() {
		return users;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return getStreet()+" "+getHouseNumber()+", "+getTown()+", "+getPostalCode();
	}
}
