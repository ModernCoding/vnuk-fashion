package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsColor {
	private int id;
	
	@NotNull
	private int productId;
	
	private int colorId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
	
	
}
