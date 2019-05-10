package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class ProductsGender {
	private Long id;
	
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
	
}
