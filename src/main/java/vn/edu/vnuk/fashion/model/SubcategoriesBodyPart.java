package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class SubcategoriesBodyPart {
	private long id;
	
	@NotNull
	private Subcategory subcategory;
	
	@NotNull
	private BodyPart bodyPart;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
