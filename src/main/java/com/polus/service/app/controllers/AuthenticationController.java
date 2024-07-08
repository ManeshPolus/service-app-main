package com.polus.service.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.polus.service.app.dto.LoginRequest;
import com.polus.service.app.dto.LoginResponse;
import com.polus.service.app.dto.SignUpRequest;
import com.polus.service.app.repository.EmployeeRepository;
import com.polus.service.app.services.AuthenticationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	

    @Autowired
    private AuthenticationService authService;
    
    @Autowired
    EmployeeRepository employee;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest) {
        
    	authService.signUp(signUpRequest);
        return new ResponseEntity<>("Sign up successful", HttpStatus.CREATED);
    }

   /* @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
    	
    	Employee employee = authService.authenticate(loginRequest);
        if (employee != null && employee.getPassword().equals(loginRequest.getPassword())) {            
            return ResponseEntity.ok(employee);
        } 
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }*/
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.authenticate(loginRequest);
        if (loginResponse != null) {
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/assignRole")
    public ResponseEntity<String> assignRoleToEmployee(@RequestParam Integer employeeId,@RequestParam Integer roleId) {
        
    	authService.assignRoleToEmployee(employeeId, roleId);
        return ResponseEntity.ok("Role assigned to employee");
    }
    
    /*@PostMapping("/login/{id}/generateTicket")
    public ResponseEntity<?> generateTicket(@RequestBody GenerateTicket ticket) {
		
    	authService.generateTicket(ticket);
    	return ResponseEntity.ok(ticket);
    	
    }*/
}
