package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsColor {
	private Long id;
	
	@NotNull
	private Product product;
	
	private Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

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

}
