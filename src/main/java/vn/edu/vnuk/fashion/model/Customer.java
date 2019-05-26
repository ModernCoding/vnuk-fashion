package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Customer {
	private Long id;
	
	@NotNull
	private Long titleId;
	
	@NotNull
	@Size(min = 1, message="Label is mandatory")
	private String label;
	
	@NotNull
	@Size(min = 1, message="Label is mandatory")
	private String address;
	
	@NotNull
	@Size(min = 1, message="Label is mandatory")
	private String phone;
	
	private String email;
	
	private Title title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}
	
	public Long getTitleId() {
		return titleId;
	}

	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	@Override
	public String toString() {
		return "Customer [id=" + id + ", title=" + title + ", titleId=" + titleId + ", label=" + label + ", address="
				+ address + ", phone=" + phone + ", email=" + email + "]";
	}
	
}
