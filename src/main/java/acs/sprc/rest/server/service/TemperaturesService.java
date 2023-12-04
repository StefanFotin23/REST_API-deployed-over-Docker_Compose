package acs.sprc.rest.server.service;

import acs.sprc.rest.database.DbService;
import acs.sprc.rest.entities.Temperature;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class TemperaturesService {

    @Autowired
    private final DbService dbService;

    public Long addTemperature(Temperature temperature) {
        return dbService.addTemperature(temperature);
    }

    public List<Temperature> getTemperatures(Double lat, Double lon, LocalDate from, LocalDate until) {
        return dbService.getTemperatures(lat, lon, from, until);
    }

    public List<Temperature> getTemperaturesByCity(Long idOras, LocalDate from, LocalDate until) {
        return dbService.getTemperaturesByCity(idOras, from, until);
    }

    public List<Temperature> getTemperaturesByCountry(Long idTara, LocalDate from, LocalDate until) {
        return dbService.getTemperaturesByCountry(idTara, from, until);
    }

    public boolean updateTemperature(Long id, Temperature temperature) {
        return dbService.updateTemperature(id, temperature);
    }

    public boolean deleteTemperature(Long id) {
        return dbService.deleteTemperature(id);
    }
}
