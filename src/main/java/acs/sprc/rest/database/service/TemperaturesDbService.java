package acs.sprc.rest.database.service;

import acs.sprc.rest.database.repository.TemperaturesRepository;
import acs.sprc.rest.entities.Temperature;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
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
        try {
            Long id = (long) (getEntitiesCounterFromDb() + 1);
            temperature.setId(id);
            Temperature savedTemperature = repository.save(temperature);
            return savedTemperature.getId();
        } catch (Exception e) {
            logger.info(e.toString());
            return 0L;
        }
    }

    public List<Temperature> getTemperaturesByDate(String from, String until, List<Temperature> temperatures) {

        Stream<Temperature> temperaturesStream = temperatures.stream();

        if (from != null) {
            temperaturesStream = temperaturesStream.filter(temperature -> dateAfter(temperature.getTimestamp(), from));
        }

        if (until != null) {
            temperaturesStream = temperaturesStream.filter(temperature -> dateBefore(temperature.getTimestamp(), until));
        }

        return temperaturesStream.toList();
    }

    public List<Temperature> getTemperaturesByCity(Long idOras, String from, String until) {
        logger.info("getTemperaturesByCity idOras=" + idOras + " from=" + from + " until=" + until);
        List<Temperature> temperatures = repository.findAll();
        Stream<Temperature> temperaturesStream = temperatures.stream();

        temperaturesStream = temperaturesStream.filter(temperature ->
                temperature.getIdOras() != null &&
                temperature.getIdOras().equals(idOras)
        );

        if (from != null) {
            temperaturesStream = temperaturesStream.filter(temperature -> dateAfter(temperature.getTimestamp(), from));
        }

        if (until != null) {
            temperaturesStream = temperaturesStream.filter(temperature -> dateBefore(temperature.getTimestamp(), until));
        }

        return temperaturesStream.toList();
    }

    public boolean updateTemperature(Long id, Temperature updatedTemperature) {
        logger.info("updateTemperature id=" + id + " temperature " + updatedTemperature.toString());
        if (updatedTemperature.getId() == null) {
            return false;
        } else {
            if (!updatedTemperature.getId().equals(id)) {
                return false;
            }
        }

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

    public List<Temperature> getAllTemperatures() {
        logger.info("getAllTemperatures");
        return repository.findAll();
    }

    private int getEntitiesCounterFromDb() {
        return getAllTemperatures().size();
    }

    // This function gets 2 Strings in format "yyyy-MM-dd" and returns true / false
    // if first date is AFTER second date chronologically
    private boolean dateAfter(String first, String second) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(first, formatter);
        LocalDate date2 = LocalDate.parse(second, formatter);
        return date1.isAfter(date2);
    }

    // This function gets 2 Strings in format "yyyy-MM-dd" and returns true / false
    // if first date is BEFORE second date chronologically
    private boolean dateBefore(String first, String second) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(first, formatter);
        LocalDate date2 = LocalDate.parse(second, formatter);
        return date1.isBefore(date2);
    }
}
