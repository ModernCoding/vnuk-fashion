package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsColor {
	private long id;
	
	@NotNull
	private Product product;
	
	private Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

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
