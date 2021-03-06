package com.capg.ftb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int adminId;
	
	
	private String adminName;
	
	@Column(name="contact")
	private String adminContact;
	
	@Column(name="email")
	private String adminEmail;
	
	@Column(name="password")
	private String adminPassword;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminContact() {
		return adminContact;
	}

	public void setAdminContact(String adminContact) {
		this.adminContact = adminContact;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public Admin() {
		super();
	}

	public Admin(String adminName, String adminContact, String adminEmail, String adminPassword) {
		super();
		this.adminName = adminName;
		this.adminContact = adminContact;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
	}
	
	
}
