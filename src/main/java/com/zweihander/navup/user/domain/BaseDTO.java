package com.zweihander.navup.user.domain;

import java.sql.Date;


public class BaseDTO {
protected Long id;
	
	protected String username;
	
	protected String password;
	
	protected String firstname;
	
	protected String lastname;
	
	protected String email;

	protected String cell_number;
	
	protected String mac_address;

	protected boolean activated = true;
	
	protected boolean is_admin;
	
	protected Date last_modified_date;
	
	protected Date created_date;

	public BaseDTO() {
		super();
	}
	
}
