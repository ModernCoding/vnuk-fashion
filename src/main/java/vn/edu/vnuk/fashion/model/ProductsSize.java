package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsSize {
	private int id;
	
	@NotNull
	private int productId;
	
	private int sizeId;
	
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
	
	public int getSizeId() {
		return sizeId;
	}
	
	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}
}
