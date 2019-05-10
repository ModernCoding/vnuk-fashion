package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class Maker {
	private long id;
	
	@NotNull
	private String lable;
	
	@NotNull
	private String address;
	
	@NotNull
	private String phone;
	
	private String email;
	
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
