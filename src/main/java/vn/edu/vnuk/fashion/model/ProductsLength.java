package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsLength {
	private int id;
	
	@NotNull
	private int productId;
	
	private int lengthId;

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

	public int getLengthId() {
		return lengthId;
	}

	public void setLengthId(int lengthId) {
		this.lengthId = lengthId;
	}
	
	
}
