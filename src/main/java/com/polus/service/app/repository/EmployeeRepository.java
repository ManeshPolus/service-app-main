package com.polus.service.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.polus.service.app.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Employee findByUsername(String username);

	boolean existsByUsername(String username);

	@Query("SELECT e FROM Employee e INNER JOIN FETCH e.employeeRoles er INNER JOIN FETCH er.role r WHERE e.username = :username")
	Employee findByUsernameWithRoles(@Param("username") String username);
}
