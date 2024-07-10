package com.polus.service.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
	String firstname;
	String lastname;
	String email;
	String designation;
	String state;
	String country;
	String phoneNumber;
	String username;
	String password;
}
