package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Subcategory {
	private Long id;
	
	@NotNull
	private Category category;
	
	@NotNull
	@Size(min = 1, message="Label is mandatory")
    private String label;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
