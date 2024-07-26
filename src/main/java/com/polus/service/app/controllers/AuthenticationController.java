package com.polus.service.app.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.polus.service.app.dto.LoginRequest;
import com.polus.service.app.dto.LoginResponse;
import com.polus.service.app.dto.SignUpRequest;
import com.polus.service.app.exceptions.UsernameAlreadyExistsException;
import com.polus.service.app.repository.EmployeeRepository;
import com.polus.service.app.services.AuthenticationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	private Logger logger = LogManager.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationService authService;

	@Autowired
	EmployeeRepository employee;

	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest) {
		return authService.signUp(signUpRequest);
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
		LoginResponse loginResponse = authService.authenticate(loginRequest);
		if (loginResponse == null) {
			logger.info("Username or password is incorrect");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password is incorrect");
		}
		return ResponseEntity.ok(loginResponse);
	}

	@ExceptionHandler(UsernameAlreadyExistsException.class)
	public ResponseEntity<String> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
