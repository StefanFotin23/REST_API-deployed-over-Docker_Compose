package acs.sprc.rest.database.service;

import acs.sprc.rest.database.repository.TemperaturesRepository;
import acs.sprc.rest.entities.Temperature;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class TemperaturesDbService {
    @Autowired
    private final TemperaturesRepository repository;
    private final Logger logger = Logger.getLogger("TemperaturesDbService");

    public Long addTemperature(Temperature temperature) {
        logger.info("addTemperature temperature " + temperature.toString());
        Temperature savedTemperature = repository.save(temperature);
        return savedTemperature.getId();
    }

    public List<Temperature> getTemperatures(Double lat, Double lon, Date from, Date until) {
        logger.info("getTemperatures lat=" + lat + " lon=" + lon + " from=" + from + " until=" + until);
        List<Temperature> temperatures = repository.findAll();
        Stream<Temperature> temperaturesStream = temperatures.stream();

//        City city; // city where the temperature was recorded
//        if (lat != null) {
//            temperaturesStream = temperaturesStream.filter(temperature -> Double.compare(city.getLatitudine(), lat) == 0);
//        }
//
//        if (lon != null) {
//            temperaturesStream = temperaturesStream.filter(temperature -> Double.compare(city.getLongitudine(), lon) == 0);
//        }

        if (from != null) {
            temperaturesStream = temperaturesStream.filter(temperature -> temperature.getTimestamp().after(from));
        }

        if (until != null) {
            temperaturesStream = temperaturesStream.filter(temperature -> temperature.getTimestamp().before(until));
        }

        return temperaturesStream.toList();
    }

    public List<Temperature> getTemperaturesByCity(Long idOras, Date from, Date until) {
        logger.info("getTemperaturesByCity idOras=" + idOras + " from=" + from + " until=" + until);
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
        logger.info("getTemperaturesByCountry idTara=" + idTara + " from=" + from + " until=" + until);
        List<Temperature> temperatures = repository.findAll();
        Stream<Temperature> temperaturesStream = temperatures.stream();

//        Country country; // country where the temperature was recorded
//        temperaturesStream = temperaturesStream.filter(temperature -> country.getId().equals(idTara));

        if (from != null) {
            temperaturesStream = temperaturesStream.filter(temperature -> temperature.getTimestamp().after(from));
        }

        if (until != null) {
            temperaturesStream = temperaturesStream.filter(temperature -> temperature.getTimestamp().before(until));
        }

        return temperaturesStream.toList();
    }

    public boolean updateTemperature(Long id, Temperature updatedTemperature) {
        logger.info("updateTemperature id=" + id + " temperature " + updatedTemperature.toString());
        Optional<Temperature> existingTemperature = repository.findById(id);
        if (existingTemperature.isPresent()) {
            repository.save(updatedTemperature);
            return true;
        }
        return false;
    }

    public boolean deleteTemperature(Long id) {
        logger.info("deleteTemperature id=" + id);
        Optional<Temperature> temperatureToDelete = repository.findById(id);
        if (temperatureToDelete.isPresent()) {
            repository.delete(temperatureToDelete.get());
            return true;
        }
        return false;
    }
}
