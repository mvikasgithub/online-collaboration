package com.niitbejai.onlinecollaboration.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

/**
 * @author mvikas
 *
 */
@Component 
@Entity
public class User_Detail implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1005038262019059223L;
	@Id
	private int userid;
	@NotEmpty
	private String password;	
	@NotEmpty
	private String fname;
	@NotEmpty
	private String sname;
	@NotEmpty
	private String email;
	@NotEmpty
	private String Address;
	@NotEmpty
	private String city;
	@NotEmpty
	private String state;
	@NotEmpty
	private String zip;
	@NotEmpty
	@NotNull
	private String phoneno;
	@NotEmpty
	private String role;
	private boolean active = true;
	
	/*
	 * Getters and Setters
	 */
	
	
	public String getPassword() {
		return password;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	@Override
	public String toString() {
		return "User_Detail [userid=" + userid + ", password=" + password + ", fname=" + fname + ", sname=" + sname
				+ ", email=" + email + ", Address=" + Address + ", city=" + city + ", state=" + state + ", zip=" + zip
				+ ", phoneno=" + phoneno + ", role=" + role + ", active=" + active + "]";
	}
	
	
	
}
