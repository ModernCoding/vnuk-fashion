package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsGender {
	private Long id;
	
	@NotNull
	private Long productId;
	
	@NotNull
	private Long genderId;
	
	@NotNull
	private Product product;
	
	@NotNull
	private Gender gender;

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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getGenderId() {
		return genderId;
	}

	public void setGenderId(Long genderId) {
		this.genderId = genderId;
	}

}
