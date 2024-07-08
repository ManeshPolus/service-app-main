package com.polus.service.app.dto;

import java.time.LocalDateTime;

public class SignUpRequest {
	
	 String firstname;
     String lastname;
     String email;
     String designation;
     String state;
     String country;
     String phoneNumber;
     String username;
     String password;
     LocalDateTime employeeCreatedDate;
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
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	public LocalDateTime getEmployeeCreatedDate() {
		return employeeCreatedDate;
	}
	public void setEmployeeCreatedDate(LocalDateTime employeeCreatedDate) {
		this.employeeCreatedDate = employeeCreatedDate;
	}
     
     
}
