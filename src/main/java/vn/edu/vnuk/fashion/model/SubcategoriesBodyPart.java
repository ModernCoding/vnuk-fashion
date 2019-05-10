package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class SubcategoriesBodyPart {
	private Long id;
	
	@NotNull
	private Subcategory subcategory;
	
	@NotNull
	private BodyPart bodyPart;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public BodyPart getBodyPart() {
		return bodyPart;
	}

	public void setBodyPart(BodyPart bodyPart) {
		this.bodyPart = bodyPart;
	}

}
