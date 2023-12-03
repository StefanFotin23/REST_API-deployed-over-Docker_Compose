package acs.sprc.rest.database.service;

import acs.sprc.rest.database.repository.CitiesRepository;
import acs.sprc.rest.entities.City;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        City savedCity = repository.save(city);
        return savedCity.getId();
    }

    public List<City> getAllCities() {
        logger.info("getAllCities");
        return repository.findAll();
    }

    public List<City> getCitiesByCountry(Long idTara) {
        logger.info("getCitiesByCountry idTara=" + idTara);
        List<City> cities = getAllCities();
        for (City city : cities) {
            if (city.getIdTara() != null) {
                if (!city.getIdTara().equals(idTara)) {
                    cities.remove(city);
                }
            } else {
                cities.remove(city);
            }
        }
        return cities;
    }

    public boolean updateCity(Long id, City updatedCity) {
        logger.info("updateCity id=" + id + " " + updatedCity);
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
}