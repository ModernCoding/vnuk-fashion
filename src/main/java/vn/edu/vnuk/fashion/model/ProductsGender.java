package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsGender {
	private int id;
	
	@NotNull
	private int productId;
	
	@NotNull
	private int genderId;
	
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
	
	public int getGenderId() {
		return genderId;
	}
	
	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}	
}
