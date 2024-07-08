package com.polus.service.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polus.service.app.entities.EmployeeRole;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Integer> {
}
