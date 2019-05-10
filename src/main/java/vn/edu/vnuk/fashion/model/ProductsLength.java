package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsLength {
	private Long id;
	
	@NotNull
	private Product product;
	
	private Length length;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
