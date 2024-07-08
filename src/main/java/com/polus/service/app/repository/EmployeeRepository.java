package com.polus.service.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.polus.service.app.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	Employee findByUsername(String username);
	
	@Query("SELECT e FROM Employee e LEFT JOIN FETCH e.employeeRoles er LEFT JOIN FETCH er.role WHERE e.username = :username")
    Employee findUserByUsernameWithRoles(@Param("username") String username);
}
