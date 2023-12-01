package acs.sprc.rest.server.controller;

import acs.sprc.rest.entities.City;
import acs.sprc.rest.server.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

    private final CitiesService citiesService;

    @Autowired
    public CitiesController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @PostMapping
    public ResponseEntity<Long> addCity(@RequestBody City city) {
        Long id = citiesService.addCity(city);
        return ResponseEntity.created(URI.create("/api/cities/" + id)).body(id);
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = citiesService.getAllCities();
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/country/{idTara}")
    public ResponseEntity<List<City>> getCitiesByCountry(@PathVariable Long idTara) {
        List<City> cities = citiesService.getCitiesByCountry(idTara);
        return ResponseEntity.ok(cities);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCity(@PathVariable Long id, @RequestBody City city) {
        boolean updated = citiesService.updateCity(id, city);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        boolean deleted = citiesService.deleteCity(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
