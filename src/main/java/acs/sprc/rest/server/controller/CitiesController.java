package acs.sprc.rest.server.controller;

import acs.sprc.rest.entities.City;
import acs.sprc.rest.server.service.CitiesService;
import acs.sprc.rest.utility.IdResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    private final CitiesService citiesService;
    private final Logger logger = Logger.getLogger("CitiesController");

    @Autowired
    public CitiesController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @PostMapping
    public ResponseEntity<IdResponse> addCity(@RequestBody City city) {
        Long id = 0L;
        ResponseEntity<IdResponse> response;
        try {
            id = citiesService.addCity(city);
            response = ResponseEntity.created(URI.create("/api/cities/" + id)).body(new IdResponse(id));
        } catch (Exception e) {
            response = ResponseEntity.badRequest().build();
            logger.info(e.toString());
        }
        logger.info("addCity " + city + " | response " + response);
        return response;
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = citiesService.getAllCities();
        ResponseEntity<List<City>> response = ResponseEntity.ok(cities);
        logger.info("getAllCities | response " + response);
        return response;
    }

    @GetMapping("/country/{idTara}")
    public ResponseEntity<List<City>> getCitiesByCountry(@PathVariable Long idTara) {
        List<City> cities = new ArrayList<>();
        try {
            cities = citiesService.getCitiesByCountry(idTara);
        } catch (Exception e) {
            logger.info(e.toString());
        }
        logger.info("getCitiesByCountry idTara=" + idTara + " " + cities);
        return ResponseEntity.ok(cities);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCity(@PathVariable Long id, @RequestBody City city) {
        boolean updated;
        ResponseEntity<Void> response;
        try {
            updated = citiesService.updateCity(id, city);
            response = updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (Exception e) {
            response = ResponseEntity.badRequest().build();
            logger.info(e.toString());
        }
        logger.info("updateCity id=" + id + " " + city + " | response " + response);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        boolean deleted;
        ResponseEntity<Void> response;
        try {
            deleted = citiesService.deleteCity(id);
            response = deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (Exception e) {
            response = ResponseEntity.badRequest().build();
            logger.info(e.toString());
        }
        logger.info("deleteCity id=" + id + " | response " + response);
        return response;
    }
}
