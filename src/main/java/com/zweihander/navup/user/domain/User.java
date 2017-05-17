package com.zweihander.navup.user.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

@Entity
@Table(name = "nav_user", schema = "user_module")
public class User implements Serializable {

	private static final long serialVersionUID = 6637087385433228063L;
	
	//Properties of User object to save in database
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	protected Long id;
	
	@Column(name="username", unique=true, length = 50, nullable=false)
	protected String username;
	
	@Column(name="password", length = 100, nullable=false)
	@ColumnTransformer(write = "crypt(?, gen_salt('bf',8))")
	protected String password;
	
	@Column(name="firstname", length = 50, nullable=false)
	protected String firstname;
	
	@Column(name="lastname", length = 50, nullable=false)
	protected String lastname;
	
	@Column(name="email", unique=true, length = 50, nullable=false)
	protected String email;
	
	@Column(name="cell_number", length = 12, nullable=false)
	protected String cell_number;
	
	@Column(name="activated", columnDefinition="boolean NOT NULL DEFAULT true", nullable=false)
	protected boolean activated = true;
	
	@Column(name="is_admin", columnDefinition="boolean NOT NULL DEFAULT false", nullable=false)
	protected boolean is_admin;
	
	@Column(name="last_modified_date", columnDefinition="date NOT NULL DEFAULT CURRENT_DATE", nullable=false)
	protected Date last_modified_date;
	
	@Column(name="created_date", columnDefinition="date NOT NULL DEFAULT CURRENT_DATE", nullable=false)
	protected Date created_date;
	
	@Column(name="mac_address", length=50, nullable=true)
	protected String mac_address;
	//Constructors
	
	public User() {
		super();
	}
	
	public User(String username, String password, String firstname, String lastname, String email, String cell_number) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.cell_number = cell_number;
		this.last_modified_date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		this.created_date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	}
	
	public User(String username, String password, String firstname, String lastname, String email, String cell_number, String mac_address) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.cell_number = cell_number;
		this.mac_address = mac_address;
		this.last_modified_date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		this.created_date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	}
	
	public User(String username, String password, String firstname, String lastname, String email, String cell_number,
			boolean activated, boolean is_admin) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.cell_number = cell_number;
		this.mac_address = "";
		this.activated = activated;
		this.is_admin = is_admin;
		this.last_modified_date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		this.created_date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	}
	
	public User(String username, String password, String firstname, String lastname, String email, String cell_number, String mac_address,
			boolean activated, boolean is_admin, Date last_modified_date, Date created_date) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.cell_number = cell_number;
		this.activated = activated;
		this.is_admin = is_admin;
		this.last_modified_date = last_modified_date;
		this.created_date = created_date;
	}

	public User(Long id, String username, String password, String firstname, String lastname, String email,
			String cell_number, String mac_address, boolean activated, boolean is_admin, Date last_modified_date, Date created_date) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
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
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", cell_number=" + cell_number + ", mac_address=" + mac_address + ", activated="
				+ activated + ", is_admin=" + is_admin + ", last_modified_date=" + last_modified_date
				+ ", created_date=" + created_date + "]";
	}

	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (id == null || obj == null || getClass() != obj.getClass())
			return false;
		User toCompare = (User) obj;
		return id.equals(toCompare.id);
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getLast_modified_date() {
		return last_modified_date;
	}

	public void setLast_modified_date(Date last_modified_date) {
		this.last_modified_date = last_modified_date;
	}

	public String getMac_address() {
		return mac_address;
	}

	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}
	
}
