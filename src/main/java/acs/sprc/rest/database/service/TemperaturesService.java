package acs.sprc.rest.database.service;

import acs.sprc.rest.database.repository.TemperaturesRepository;
import acs.sprc.rest.entities.City;
import acs.sprc.rest.entities.Country;
import acs.sprc.rest.entities.Temperature;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class TemperaturesService {
    @Autowired
    private final TemperaturesRepository repository;

    public Long addTemperature(Temperature temperature) {
        Temperature savedTemperature = repository.save(temperature);
        return savedTemperature.getId();
    }

    public List<Temperature> getTemperatures(Double lat, Double lon, Date from, Date until) {
        List<Temperature> temperatures = repository.findAll();
        Stream<Temperature> temperaturesStream = temperatures.stream();

        City city; // city where the temperature was recorded
        if (lat != null) {
            temperaturesStream = temperaturesStream.filter(temperature -> Double.compare(city.getLatitudine(), lat) == 0);
        }

        if (lon != null) {
            temperaturesStream = temperaturesStream.filter(temperature -> Double.compare(city.getLongitudine(), lon) == 0);
        }

        if (from != null) {
            temperaturesStream = temperaturesStream.filter(temperature -> temperature.getTimestamp().after(from));
        }

        if (until != null) {
            temperaturesStream = temperaturesStream.filter(temperature -> temperature.getTimestamp().before(until));
        }

        return temperaturesStream.toList();
    }

    public List<Temperature> getTemperaturesByCity(Long idOras, Date from, Date until) {
        List<Temperature> temperatures = repository.findAll();
        Stream<Temperature> temperaturesStream = temperatures.stream();

        temperaturesStream = temperaturesStream.filter(temperature -> temperature.getId_oras().equals(idOras));

        if (from != null) {
            temperaturesStream = temperaturesStream.filter(temperature -> temperature.getTimestamp().after(from));
        }

        if (until != null) {
            temperaturesStream = temperaturesStream.filter(temperature -> temperature.getTimestamp().before(until));
        }

        return temperaturesStream.toList();
    }

    public List<Temperature> getTemperaturesByCountry(Long idTara, Date from, Date until) {
        List<Temperature> temperatures = repository.findAll();
        Stream<Temperature> temperaturesStream = temperatures.stream();

        Country country; // country where the temperature was recorded

        temperaturesStream = temperaturesStream.filter(temperature -> country.getId().equals(idTara));

        if (from != null) {
            temperaturesStream = temperaturesStream.filter(temperature -> temperature.getTimestamp().after(from));
        }

        if (until != null) {
            temperaturesStream = temperaturesStream.filter(temperature -> temperature.getTimestamp().before(until));
        }

        return temperaturesStream.toList();
    }

    public boolean updateTemperature(Long id, Temperature updatedTemperature) {
        Optional<Temperature> existingTemperature = repository.findById(id);
        if (existingTemperature.isPresent()) {
            repository.save(updatedTemperature);
            return true;
        }
        return false;
    }

    public boolean deleteTemperature(Long id) {
        Optional<Temperature> temperatureToDelete = repository.findById(id);
        if (temperatureToDelete.isPresent()) {
            repository.delete(temperatureToDelete.get());
            return true;
        }
        return false;
    }
}
