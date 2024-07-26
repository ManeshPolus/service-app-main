package com.polus.service.app.services;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.polus.service.app.entities.Country;
import com.polus.service.app.repository.CountryRepository;

@Service
public class CountryService {

	private Logger logger = LogManager.getLogger(CountryService.class);

	@Autowired
	CountryRepository countryRepository;

	public List<Country> getAllCountries() {
		try {
			return countryRepository.findAll();
		} catch (Exception e) {
			logger.info("Error fetching countries: {}", e.getMessage());
			return null;
		}
	}
}
