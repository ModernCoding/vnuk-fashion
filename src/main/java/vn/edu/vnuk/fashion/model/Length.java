package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class Length {

	private int id;
	@NotNull
	private String label;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}
