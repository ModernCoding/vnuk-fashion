package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class Maker {
	private int id;
	@NotNull
	private String lbel;
	@NotNull
	private String address;
	@NotNull
	private String phone;
	private String email;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


}
