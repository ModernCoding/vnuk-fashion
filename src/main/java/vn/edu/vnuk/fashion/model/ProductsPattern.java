package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsPattern {
	private Long id;
	
	@NotNull
	private Long productId;
	
	@NotNull
	private Long patternId;
	
	private Product product;
	
	private Pattern pattern;

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
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

	public Long getPatternId() {
		return patternId;
	}

	public void setPatternId(Long patternId) {
		this.patternId = patternId;
	}
	
	
}
