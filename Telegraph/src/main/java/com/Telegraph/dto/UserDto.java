package com.Telegraph.dto;

public class UserDto {
	
	private String username;

	private String pass;

	private String email;

	private Boolean removed;

	private String imgcode;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setImgcode(String imgcode) {
		this.imgcode = imgcode;
	}
	public String getImgcode() {
		return imgcode;
	}

	public Boolean getRemoved() {
		return removed;
	}

	public void setRemoved(Boolean removed) {
		this.removed = removed;
	}
}
