package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class SubcategoriesBodyPart {
	private int id;
	
	@NotNull
	private int subcategoryId;
	
	@NotNull
	private int bodyPartId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public int getBodyPartId() {
		return bodyPartId;
	}

	public void setBodyPartId(int bodyPartId) {
		this.bodyPartId = bodyPartId;
	}
	
}
