package acs.sprc.rest.server.controller;

import acs.sprc.rest.entities.Country;
import acs.sprc.rest.server.service.CountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/countries")
public class CountriesController {
    @Autowired
    private CountriesService countryService;
    private final Logger logger = Logger.getLogger("CountriesController");

    @PostMapping
    public ResponseEntity<Long> addCountry(@RequestBody Country country) {
        Long id = countryService.addCountry(country);
        logger.info("addCountry " + country.toString());
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        logger.info("getAllCountries");
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCountry(@PathVariable Long id, @RequestBody Country country) {
        logger.info("updateCountry id=" + id + " country " + country.toString());
        if (countryService.updateCountry(id, country)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        logger.info("deleteCountry id=" + id);
        if (countryService.deleteCountry(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
