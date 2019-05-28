package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class SubcategoriesBodyPart {
	private Long id;
	
	private Subcategory subcategory;
	
	@NotNull
	private Long subcategoryId;
	
	private BodyPart bodyPart;
	
	@NotNull
	private Long bodyPartId;

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

	public Long getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(Long subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public Long getBodyPartId() {
		return bodyPartId;
	}

	public void setBodyPartId(Long bodyPartId) {
		this.bodyPartId = bodyPartId;
	}
	

}
