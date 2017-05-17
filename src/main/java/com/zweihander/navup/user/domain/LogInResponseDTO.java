package com.zweihander.navup.user.domain;

public class LogInResponseDTO extends BaseDTO{
	private String username;
	private String email;
	private Boolean isAdmin;
	public LogInResponseDTO() {
		super();
	}
	public LogInResponseDTO(String username, String email, Boolean isAdmin) {
		super();
		this.username = username;
		this.email = email;
		this.isAdmin = isAdmin;
	}
	public LogInResponseDTO(User user) {
		super();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.isAdmin = user.isIs_admin();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
