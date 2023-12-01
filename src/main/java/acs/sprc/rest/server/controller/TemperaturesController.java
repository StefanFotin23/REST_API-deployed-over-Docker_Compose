package acs.sprc.rest.server.controller;

import acs.sprc.rest.entities.Temperature;
import acs.sprc.rest.server.service.TemperaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/temperatures")
public class TemperaturesController {
    @Autowired
    private TemperaturesService temperaturesService;

    @PostMapping
    public ResponseEntity<Long> addTemperature(@RequestBody Temperature temperature) {
        Long id = temperaturesService.addTemperature(temperature);
        return ResponseEntity.status(201).body(id);
    }

    @GetMapping
    public ResponseEntity<List<Temperature>> getTemperatures(
            @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double lon,
            @RequestParam(required = false) Date from,
            @RequestParam(required = false) Date until) {
        List<Temperature> temperatures = temperaturesService.getTemperatures(lat, lon, from, until);
        return ResponseEntity.status(200).body(temperatures);
    }

    @GetMapping("/cities/{idOras}")
    public ResponseEntity<List<Temperature>> getTemperaturesByCity(
            @PathVariable Long idOras,
            @RequestParam(required = false) Date from,
            @RequestParam(required = false) Date until) {
        List<Temperature> temperatures = temperaturesService.getTemperaturesByCity(idOras, from, until);
        return ResponseEntity.status(200).body(temperatures);
    }

    @GetMapping("/countries/{idTara}")
    public ResponseEntity<List<Temperature>> getTemperaturesByCountry(
            @PathVariable Long idTara,
            @RequestParam(required = false) Date from,
            @RequestParam(required = false) Date until) {
        List<Temperature> temperatures = temperaturesService.getTemperaturesByCountry(idTara, from, until);
        return ResponseEntity.status(200).body(temperatures);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTemperature(@PathVariable Long id, @RequestBody Temperature temperature) {
        if (temperaturesService.updateTemperature(id, temperature)) {
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemperature(@PathVariable Long id) {
        if (temperaturesService.deleteTemperature(id)) {
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
