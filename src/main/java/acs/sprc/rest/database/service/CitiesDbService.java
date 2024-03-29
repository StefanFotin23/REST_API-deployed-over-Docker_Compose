package acs.sprc.rest.database.service;

import acs.sprc.rest.database.repository.CitiesRepository;
import acs.sprc.rest.entities.City;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class CitiesDbService {
    @Autowired
    private final CitiesRepository repository;
    private final Logger logger = Logger.getLogger("CitiesDbService");

    public Long addCity(City city) {
        logger.info("addCity " + city);
        try {
            Long id = (long) (getEntitiesCounterFromDb() + 1);
            city.setId(id);
            City savedCity = repository.save(city);
            return savedCity.getId();
        } catch (Exception e) {
            logger.info(e.toString());
            return 0L;
        }
    }

    public List<City> getAllCities() {
        logger.info("getAllCities");
        return repository.findAll();
    }

    public List<City> getCitiesByCountry(Long idTara) {
        logger.info("getCitiesByCountry idTara=" + idTara);
        List<City> allCities = getAllCities();
        List<City> filteredCities = new ArrayList<>();

        for (City city : allCities) {
            if (city.getIdTara() != null) {
                if (city.getIdTara().equals(idTara)) {
                    filteredCities.add(city);
                }
            }
        }
        return filteredCities;
    }

    public boolean updateCity(Long id, City updatedCity) {
        logger.info("updateCity id=" + id + " " + updatedCity);
        if (updatedCity.getId() == null) {
            return false;
        } else {
            if (!updatedCity.getId().equals(id)) {
                return false;
            }
        }

        Optional<City> existingCity = repository.findById(id);
        if (existingCity.isPresent()) {
            repository.save(updatedCity);
            return true;
        }
        return false;
    }

    public boolean deleteCity(Long id) {
        logger.info("deleteCity id=" + id);
        Optional<City> cityToDelete = repository.findById(id);
        if (cityToDelete.isPresent()) {
            repository.delete(cityToDelete.get());
            return true;
        }
        return false;
    }

    private int getEntitiesCounterFromDb() {
        return getAllCities().size();
    }
}