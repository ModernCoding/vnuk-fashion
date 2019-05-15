package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsLength {
	private Long id;

	@NotNull
	private Long productId;
	
	@NotNull
	private Long lengthId;
	
	private Product product;
	private Length length;

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

	public Length getLength() {
		return length;
	}

	public void setLength(Length length) {
		this.length = length;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getLengthId() {
		return lengthId;
	}

	public void setLengthId(Long lengthId) {
		this.lengthId = lengthId;
	}
	
	
}
