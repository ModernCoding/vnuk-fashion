package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class Subcategory {
	private int id;
	
	@NotNull
	private int categoryId;
	
	@NotNull
	private String label;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
