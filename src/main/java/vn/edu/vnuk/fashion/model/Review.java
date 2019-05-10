package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class Review {
	private long id;
	
	@NotNull
	private Order order;
	
	@NotNull
	private int rating;

	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
