package com.zweihander.navup.user.domain;

public class UserDTO extends BaseDTO{

	public UserDTO(){
		
	}
	
	public UserDTO(String username, String firstname, String lastname, String email, String cell_number, String mac_address) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.cell_number = cell_number;
		this.mac_address = mac_address;
	}

	public UserDTO(User user){
		super();
		this.username = user.getUsername();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.email = user.getEmail();
		this.cell_number = user.getCell_number();
		this.mac_address = user.getMac_address();
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	
	public String getMac_address(){
		return mac_address;
	}
	public void setMac_address(String mac_address){
		this.mac_address = mac_address;
	}
}
