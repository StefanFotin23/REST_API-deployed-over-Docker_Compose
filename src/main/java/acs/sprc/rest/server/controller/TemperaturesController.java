package acs.sprc.rest.server.controller;

import acs.sprc.rest.entities.Temperature;
import acs.sprc.rest.server.service.TemperaturesService;
import acs.sprc.rest.utility.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/temperatures")
public class TemperaturesController {
    @Autowired
    private TemperaturesService temperaturesService;
    private final Logger logger = Logger.getLogger("TemperaturesController");

    @PostMapping
    public ResponseEntity<IdResponse> addTemperature(@Valid @RequestBody Temperature temperature) {
        if (!isValid(temperature)) {
            return ResponseEntity.badRequest().build();
        }

        Long id = 0L;
                ResponseEntity<IdResponse> response;
        try {
            id = temperaturesService.addTemperature(temperature);
            if (id == 0) {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            response = ResponseEntity.badRequest().build();
            logger.info(e.toString());
            logger.info("addTemperature " + temperature + " | response " + response);
            return response;
        }
        response = ResponseEntity.status(201).body(new IdResponse(id));
        logger.info("addTemperature " + temperature + " | response " + response);
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Temperature>> getTemperatures(
            @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double lon,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String until) {
        List<Temperature> temperatures = temperaturesService.getTemperatures(lat, lon, from, until);
        ResponseEntity<List<Temperature>> response = ResponseEntity.status(200).body(temperatures);
        logger.info("getTemperatures lat=" + lat + " lon=" + lon + " from=" + from + " until=" + until + " | response " + response);
        return response;
    }

    @GetMapping("/cities/{idOras}")
    public ResponseEntity<List<Temperature>> getTemperaturesByCity(
            @PathVariable Long idOras,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String until) {
        List<Temperature> temperatures = temperaturesService.getTemperaturesByCity(idOras, from, until);
        ResponseEntity<List<Temperature>> response = ResponseEntity.status(200).body(temperatures);
        logger.info("getTemperaturesByCity idOras=" + idOras + " from=" + from + " until=" + until + " | response " + response);
        return response;
    }

    @GetMapping("/countries/{idTara}")
    public ResponseEntity<List<Temperature>> getTemperaturesByCountry(
            @PathVariable Long idTara,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String until) {
        List<Temperature> temperatures = temperaturesService.getTemperaturesByCountry(idTara, from, until);
        ResponseEntity<List<Temperature>> response = ResponseEntity.status(200).body(temperatures);
        logger.info("getTemperaturesByCountry idTara=" + idTara + " from=" + from + " until=" + until + " | response " + response);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTemperature(@PathVariable Long id, @Valid @RequestBody Temperature temperature) {
        if (!isValid(temperature)) {
            return ResponseEntity.badRequest().build();
        }

        ResponseEntity<Void> response;
        if (temperaturesService.updateTemperature(id, temperature)) {
            response = ResponseEntity.status(200).build();
        } else {
            response = ResponseEntity.status(404).build();
        }
        logger.info("updateTemperature id=" + id + " " + temperature + " | response " + response);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemperature(@PathVariable Long id) {
        ResponseEntity<Void> response;
        if (temperaturesService.deleteTemperature(id)) {
            response = ResponseEntity.status(200).build();
        } else {
            response = ResponseEntity.status(404).build();
        }
        logger.info("deleteTemperature id=" + id + " | response " + response);
        return response;
    }

    boolean isValid(Temperature temperature) {
        return temperature.getIdOras() != null && temperature.getValoare() != null;
    }
}
