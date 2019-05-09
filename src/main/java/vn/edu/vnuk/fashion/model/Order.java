package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class Order {
	private int id;
	
	@NotNull
	private int customerId;
	
	@NotNull
	private int priceId;
	
	@NotNull
	private int qty;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	
}
