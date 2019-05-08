package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class Height {
	private int id;
	@NotNull
	private String lable;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	
	
}
