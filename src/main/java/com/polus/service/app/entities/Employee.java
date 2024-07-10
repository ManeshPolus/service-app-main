package com.polus.service.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "EMPLOYEE")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_ID")
	private Integer employeeId;

	@Column(name = "FIRST_NAME")
	private String firstname;

	@Column(name = "LAST_NAME")
	private String lastname;

	@Column(name = "E_MAIL")
	private String email;

	@Column(name = "DESIGNATION")
	private String designation;

	@Column(name = "STATE")
	private String state;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "USERNAME", unique = true)
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@OneToMany(mappedBy = "EMPLOYEE", fetch = FetchType.LAZY)
	private Set<EmployeeRole> employeeRoles;
}