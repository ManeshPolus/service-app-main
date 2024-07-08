package com.polus.service.app.dto;

import java.util.List;

public class LoginResponse {

	    private Integer employeeId;
	    private String username;
	    private String firstname;
	    private String lastname;
	    private String email;
	    private String designation;
	    private String state;
	    private String country;
	    private String phoneNumber;
	    private List<RoleDto> roles;
	    
		public Integer getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(Integer employeeId) {
			this.employeeId = employeeId;
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
		public List<RoleDto> getRoles() {
			return roles;
		}
		public void setRoles(List<RoleDto> roles) {
			this.roles = roles;
		}
	    
	    
}
