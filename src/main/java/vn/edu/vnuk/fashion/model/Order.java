package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class Order {
	private Long id;
	
	@NotNull
	private Long customerId;
	
	@NotNull
	private Long priceId;
	
	@NotNull
	private Customer customer;
	
	@NotNull
	private Price price;
	
	@NotNull
	private int qty;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}
	
	
	
}
