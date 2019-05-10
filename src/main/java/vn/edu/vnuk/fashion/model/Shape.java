package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class Shape {

	private long id;

	@NotNull
	private String lable;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
}
