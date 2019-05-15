package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsColor {
	private Long id;

	@NotNull
	private Long productId;
	
	@NotNull
	private Long colorId;
	
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getColorId() {
		return colorId;
	}

	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}
	
	
}
