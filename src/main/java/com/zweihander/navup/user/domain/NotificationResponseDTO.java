package com.zweihander.navup.user.domain;

public class NotificationResponseDTO extends BaseDTO{
	
	protected String username;
	protected String email;
	protected String cell_number;
	
	public NotificationResponseDTO() {
		super();
	}

	public NotificationResponseDTO(String username, String email, String cell_number) {
		super();
		this.username = username;
		this.email = email;
		this.cell_number = cell_number;
	}

	public NotificationResponseDTO(User user) {
		super();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.cell_number = user.getCell_number();
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

	public String getCell_number() {
		return cell_number;
	}

	public void setCell_number(String cell_number) {
		this.cell_number = cell_number;
	}	
}
