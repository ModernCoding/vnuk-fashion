package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsSize {
	private Long id;
	
	@NotNull
	private Long productId;
	
	@NotNull
	private Long sizeId;
	
	private Product product;
	
	private Size size;

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

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getSizeId() {
		return sizeId;
	}

	public void setSizeId(Long sizeId) {
		this.sizeId = sizeId;
	}	
	
}
