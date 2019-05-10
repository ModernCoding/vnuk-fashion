package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class Sleeve {
	private Long id;
	
	@NotNull
	private String label;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}	
