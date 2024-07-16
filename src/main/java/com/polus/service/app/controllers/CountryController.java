package com.polus.service.app.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.polus.service.app.entities.Country;
import com.polus.service.app.services.CountryService;

@RestController
@RequestMapping("/api/country")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping("/getallcountries")
	public ResponseEntity<Object> getAllCountries() {
		List<Country> country = countryService.getAllCountries();
		if (country == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("List not found");
		}
		return ResponseEntity.ok(country);
	}
}
