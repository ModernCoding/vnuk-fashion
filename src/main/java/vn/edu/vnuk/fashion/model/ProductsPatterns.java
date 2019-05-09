package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsPatterns {
	private int id;
	@NotNull
	private int productId;
	private int patternId;
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
	
	public int getPatternId() {
		return patternId;
	}
	
	public void setPatternId(int patternId) {
		this.patternId = patternId;
	}	
}
