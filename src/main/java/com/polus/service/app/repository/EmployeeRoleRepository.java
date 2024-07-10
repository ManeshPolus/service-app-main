package com.polus.service.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.polus.service.app.entities.EmployeeRole;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Integer> {
	@Query("SELECT er FROM EmployeeRole er INNER JOIN er.role r WHERE er.employee.employeeId = :employeeId")
	EmployeeRole findRolesByEmployeeId(@Param("employeeId") Long employeeId);

}
