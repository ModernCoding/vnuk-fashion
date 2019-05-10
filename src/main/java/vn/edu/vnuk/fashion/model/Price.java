package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class Price {
	private long id;
	
	@NotNull
	private ProductsSize productsSize;
	
	@NotNull
	private ProductsColor productsColor;
	
	private ProductsPattern productsPattern;
	
	private ProductsLength productsLength;
	
	@NotNull
	private PriceType priceType;
	
	@NotNull
	private float value;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProductsSize getProductsSize() {
		return productsSize;
	}

	public void setProductsSize(ProductsSize productsSize) {
		this.productsSize = productsSize;
	}

	public ProductsColor getProductsColor() {
		return productsColor;
	}

	public void setProductsColor(ProductsColor productsColor) {
		this.productsColor = productsColor;
	}

	public ProductsPattern getProductsPattern() {
		return productsPattern;
	}

	public void setProductsPattern(ProductsPattern productsPattern) {
		this.productsPattern = productsPattern;
	}

	public ProductsLength getProductsLength() {
		return productsLength;
	}

	public void setProductsLength(ProductsLength productsLength) {
		this.productsLength = productsLength;
	}

	public PriceType getPriceType() {
		return priceType;
	}

	public void setPriceType(PriceType priceType) {
		this.priceType = priceType;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
	
}
