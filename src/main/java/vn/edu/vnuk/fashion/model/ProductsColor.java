package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsColor {
	private long id;
	
	@NotNull
	private Product product;
	
	private Color color;

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

}
