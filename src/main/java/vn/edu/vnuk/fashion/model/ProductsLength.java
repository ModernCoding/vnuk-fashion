package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsLength {
	private long id;
	
	@NotNull
	private Product product;
	
	private Length length;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Length getLength() {
		return length;
	}

	public void setLength(Length length) {
		this.length = length;
	}
	
}
