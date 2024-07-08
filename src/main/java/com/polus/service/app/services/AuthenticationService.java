package com.polus.service.app.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.polus.service.app.dto.LoginRequest;
import com.polus.service.app.dto.LoginResponse;
import com.polus.service.app.dto.RoleDto;
import com.polus.service.app.dto.SignUpRequest;
import com.polus.service.app.entities.Employee;
import com.polus.service.app.entities.EmployeeRole;
import com.polus.service.app.entities.Role;
import com.polus.service.app.repository.EmployeeRepository;
import com.polus.service.app.repository.EmployeeRoleRepository;
import com.polus.service.app.repository.RoleRepository;

@Service
public class AuthenticationService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmployeeRoleRepository employeeRoleRepository;

    public static final Integer DEFAULT_ROLE_ID = 1;

    public void signUp(SignUpRequest signUpRequest) {
        
        Employee employee = new Employee();
        employee.setFirstname(signUpRequest.getFirstname());
        employee.setLastname(signUpRequest.getLastname());
        employee.setEmail(signUpRequest.getEmail());
        employee.setDesignation(signUpRequest.getDesignation());
        employee.setState(signUpRequest.getState());
        employee.setCountry(signUpRequest.getCountry());
        employee.setPhoneNumber(signUpRequest.getPhoneNumber());
        employee.setUsername(signUpRequest.getUsername());
        employee.setPassword(signUpRequest.getPassword());
        employee.setEmployeeCreatedDate(LocalDateTime.now());

        Employee savedEmployee = employeeRepository.save(employee);
        
        assignDefaultRoleToEmployee(savedEmployee.getEmployeeId());
    }

    private void assignDefaultRoleToEmployee(Integer employeeId) {
    	
        Role role = roleRepository.findById(DEFAULT_ROLE_ID)
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        EmployeeRole employeeRole = new EmployeeRole();
        employeeRole.setEmployee(employee);
        employeeRole.setRole(role);

        employeeRoleRepository.save(employeeRole);
    }

    /*public Employee authenticate(LoginRequest loginRequest) {
    	
        Employee employee = employeeRepository.findByUsername(loginRequest.getUsername());
        return employee;
    }*/
    
    public LoginResponse authenticate(LoginRequest loginRequest) {
        Employee employee = employeeRepository.findUserByUsernameWithRoles(loginRequest.getUsername());
        if (employee != null && employee.getPassword().equals(loginRequest.getPassword())) {
            List<RoleDto> roles = employee.getEmployeeRoles().stream()
                .map(employeeRole -> {
                    RoleDto roleDto = new RoleDto();
                    roleDto.setRoleId(employeeRole.getRole().getRoleId());
                    roleDto.setRoleName(employeeRole.getRole().getRoleName());
                    roleDto.setRoleDescription(employeeRole.getRole().getRoleDescription());
                    return roleDto;
                }).collect(Collectors.toList());

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setEmployeeId(employee.getEmployeeId());
            loginResponse.setUsername(employee.getUsername());
            loginResponse.setFirstname(employee.getFirstname());
            loginResponse.setLastname(employee.getLastname());
            loginResponse.setEmail(employee.getEmail());
            loginResponse.setDesignation(employee.getDesignation());
            loginResponse.setState(employee.getState());
            loginResponse.setCountry(employee.getCountry());
            loginResponse.setPhoneNumber(employee.getPhoneNumber());
            loginResponse.setRoles(roles);

            return loginResponse;
        }
        return null;
    }
    

    public void assignRoleToEmployee(Integer employeeId, Integer roleId) {
    	
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        EmployeeRole employeeRole = new EmployeeRole();
        employeeRole.setEmployee(employee);
        employeeRole.setRole(role);

        employeeRoleRepository.save(employeeRole);
    }
}
