package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class Price {
	private int id;
	
	@NotNull
	private int productsSizeId;
	
	@NotNull
	private int productsColorId;
	
	private int productsPatternId;
	
	private int productsLengthId;
	
	@NotNull
	private int sellerId;
	
	@NotNull
	private int priceTypeId;
	
	@NotNull
	private float value;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getProductsSizeId() {
		return productsSizeId;
	}
	
	public void setProductsSizeId(int productsSizeId) {
		this.productsSizeId = productsSizeId;
	}
	
	public int getProductsColorId() {
		return productsColorId;
	}
	
	public void setProductsColorId(int productsColorId) {
		this.productsColorId = productsColorId;
	}
	
	public int getProductsPatternId() {
		return productsPatternId;
	}
	
	public void setProductsPatternId(int productsPatternId) {
		this.productsPatternId = productsPatternId;
	}
	
	public int getProductsLengthId() {
		return productsLengthId;
	}
	
	public void setProductsLengthId(int productsLengthId) {
		this.productsLengthId = productsLengthId;
	}
	
	public int getSellerId() {
		return sellerId;
	}
	
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	
	public int getPriceTypeId() {
		return priceTypeId;
	}
	
	public void setPriceTypeId(int priceTypeId) {
		this.priceTypeId = priceTypeId;
	}
	
	public float getValue() {
		return value;
	}
	
	public void setValue(float value) {
		this.value = value;
	}		
}
