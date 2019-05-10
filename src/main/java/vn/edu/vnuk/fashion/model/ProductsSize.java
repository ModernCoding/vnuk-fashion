package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsSize {
	private long id;
	
	@NotNull
	private Product product;
	
	private Size size;

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

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}
	
	
}
