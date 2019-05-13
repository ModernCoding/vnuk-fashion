package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Subcategory {
	private Long id;
	
	@NotNull
	private Long categoryId;
	
	@NotNull
	@Size(min = 1, message="Label is mandatory")
    private String label;
	
	private Category category;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
