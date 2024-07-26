package com.polus.service.app.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.polus.service.app.Constants;
import com.polus.service.app.dto.LoginRequest;
import com.polus.service.app.dto.LoginResponse;
import com.polus.service.app.dto.RoleDto;
import com.polus.service.app.dto.SignUpRequest;
import com.polus.service.app.entities.Employee;
import com.polus.service.app.entities.EmployeeRole;
import com.polus.service.app.entities.Role;
import com.polus.service.app.entities.Country;
import com.polus.service.app.exceptions.UsernameAlreadyExistsException;
import com.polus.service.app.repository.CountryRepository;
import com.polus.service.app.repository.EmployeeRepository;
import com.polus.service.app.repository.EmployeeRoleRepository;
import com.polus.service.app.repository.RoleRepository;

@Service
public class AuthenticationService {

	private Logger logger = LogManager.getLogger(AuthenticationService.class);

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	EmployeeRoleRepository employeeRoleRepository;

	@Autowired
	CountryRepository countryRespository;

	public ResponseEntity<String> signUp(SignUpRequest signUpRequest) {
		try {
			if (employeeRepository.existsByUsername(signUpRequest.getUsername())) {
				throw new UsernameAlreadyExistsException("Username already exists");
			} else {
				Employee employee = new Employee();
				Optional<Country> country = countryRespository.findById(signUpRequest.getCountryCode());
				employee.setFirstname(signUpRequest.getFirstname());
				employee.setLastname(signUpRequest.getLastname());
				employee.setEmail(signUpRequest.getEmail());
				employee.setDesignation(signUpRequest.getDesignation());
				employee.setState(signUpRequest.getState());
				employee.setCountryCode(country.get());
				employee.setPhoneNumber(signUpRequest.getPhoneNumber());
				employee.setUsername(signUpRequest.getUsername());
				employee.setPassword(signUpRequest.getPassword());
				employee.setCreatedDate(new Date());
				Employee saveEmployee = employeeRepository.save(employee);
				assignDefaultRoleToEmployee(saveEmployee.getEmployeeId());
				logger.info("User signup successfully");
				return ResponseEntity.status(HttpStatus.CREATED).body("User signup successfully");
			}
		} catch (UsernameAlreadyExistsException e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occured while processing the sign up request");
		}
	}

	private void assignDefaultRoleToEmployee(Integer employeeId) {

		Role role = roleRepository.findById(Constants.DEFAULT_ROLE_ID)
				.orElseThrow(() -> new RuntimeException("Default role not found"));
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee not found"));
		EmployeeRole employeeRole = new EmployeeRole();
		employeeRole.setEmployee(employee);
		employeeRole.setRole(role);
		employeeRoleRepository.save(employeeRole);
	}

	public LoginResponse authenticate(LoginRequest loginRequest) {
		Employee employee = employeeRepository.findByUsername(loginRequest.getUsername());
		if (employee != null && employee.getPassword().equals(loginRequest.getPassword())) {
			List<RoleDto> roles = employee.getEmployeeRoles().stream().map(employeeRole -> {
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
			loginResponse.setCountryCode(employee.getCountryCode());
			loginResponse.setPhoneNumber(employee.getPhoneNumber());
			loginResponse.setCreatedDate(employee.getCreatedDate());
			loginResponse.setRoles(roles);
			return loginResponse;
		}
		return null;
	}
}
