package com.zweihander.navup.user.domain;

import java.sql.Date;


public class AdminDTO extends BaseDTO{

	public AdminDTO() {
		super();
	}

	public AdminDTO(String username, String firstname, String lastname, String email, String cell_number,
			String mac_address, boolean activated, boolean is_admin, Date last_modified_date, Date created_date) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.cell_number = cell_number;
		this.mac_address = mac_address;
		this.activated = activated;
		this.is_admin = is_admin;
		this.last_modified_date = last_modified_date;
		this.created_date = created_date;
	}

	public AdminDTO(User user) {
		super();
		this.username = user.getUsername();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.email = user.getEmail();
		this.cell_number = user.getCell_number();
		this.mac_address = user.getMac_address();
		this.activated = user.isActivated();
		this.is_admin = user.isIs_admin();
		this.last_modified_date = user.getLast_modified_date();
		this.created_date = user.getCreated_date();
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
	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public boolean isIs_admin() {
		return is_admin;
	}

	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}

	public Date getLast_modified_date() {
		return last_modified_date;
	}

	public void setLast_modified_date(Date last_modified_date) {
		this.last_modified_date = last_modified_date;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
}
