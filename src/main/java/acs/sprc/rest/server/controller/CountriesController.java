package acs.sprc.rest.server.controller;

import acs.sprc.rest.entities.Country;
import acs.sprc.rest.server.service.CountriesService;
import acs.sprc.rest.utility.IdResponse;
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
    public ResponseEntity<IdResponse> addCountry(@RequestBody Country country) {
        Long id = 0L;
        ResponseEntity<IdResponse> response;
        try {
            id = countryService.addCountry(country);
            response = new ResponseEntity<>(new IdResponse(id), HttpStatus.CREATED);
        } catch (Exception e) {
            response = ResponseEntity.badRequest().build();
            logger.info(e.toString());
        }
        logger.info("addCountry " + country + " | response " + response);
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        ResponseEntity<List<Country>> response = new ResponseEntity<>(countries, HttpStatus.OK);
        logger.info("getAllCountries | response " + response);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCountry(@PathVariable Long id, @RequestBody Country country) {
        ResponseEntity<Void> response;
        boolean updated = false;
        try {
            updated = countryService.updateCountry(id, country);
        } catch (Exception e) {
            response = ResponseEntity.badRequest().build();
            logger.info(e.toString());
            logger.info("updateCountry id=" + id + " " + country + " | response " + response);
            return response;
        }

        if (updated) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("updateCountry id=" + id + " " + country + " | response " + response);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        ResponseEntity<Void> response;
        boolean deleted = false;
        try {
            deleted = countryService.deleteCountry(id);
        } catch (Exception e) {
            response = ResponseEntity.badRequest().build();
            logger.info(e.toString());
            logger.info("deleteCountry id=" + id + " | response " + response);
            return response;
        }

        if (deleted) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("deleteCountry id=" + id + " | response " + response);
        return response;
    }
}
