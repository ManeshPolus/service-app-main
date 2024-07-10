package com.polus.service.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "EMPLOYEE_ROLE")
public class EmployeeRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
	private Role role;
}
