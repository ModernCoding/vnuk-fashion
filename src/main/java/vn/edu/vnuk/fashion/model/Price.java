package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class Price {
	private Long id;
	
	@NotNull
	private Long productsSizeId;
	
	@NotNull
	private Long productsColorId;
	
	@NotNull
	private Long productsPatternId;
	
	@NotNull
	private Long productsLengthId;
	
	@NotNull
	private Long sellerId;
	
	@NotNull
	private Long priceTypeId;
	
	private ProductsSize productsSize;
	
	private ProductsColor productsColor;
	
	private ProductsPattern productsPattern;
	
	private ProductsLength productsLength;
	
	private Seller seller;

	private PriceType priceType;
	
	@NotNull
	private float value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public Long getProductsSizeId() {
		return productsSizeId;
	}

	public void setProductsSizeId(Long productsSizeId) {
		this.productsSizeId = productsSizeId;
	}

	public Long getProductsColorId() {
		return productsColorId;
	}

	public void setProductsColorId(Long productsColorId) {
		this.productsColorId = productsColorId;
	}

	public Long getProductsPatternId() {
		return productsPatternId;
	}

	public void setProductsPatternId(Long productsPatternId) {
		this.productsPatternId = productsPatternId;
	}

	public Long getProductsLengthId() {
		return productsLengthId;
	}

	public void setProductsLengthId(Long productsLengthId) {
		this.productsLengthId = productsLengthId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getPriceTypeId() {
		return priceTypeId;
	}

	public void setPriceTypeId(Long priceTypeId) {
		this.priceTypeId = priceTypeId;
	}
	
	
	
}
