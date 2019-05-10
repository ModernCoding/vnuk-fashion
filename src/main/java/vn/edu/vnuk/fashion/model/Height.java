package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class Height {
	private int id;
	@NotNull
	private String lbel;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLbel() {
		return lbel;
	}
	public void setLbel(String lbel) {
		this.lbel = lbel;
	}
	
	
}
