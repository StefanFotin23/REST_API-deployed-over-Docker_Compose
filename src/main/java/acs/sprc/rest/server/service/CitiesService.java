package acs.sprc.rest.server.service;

import acs.sprc.rest.database.DbService;
import acs.sprc.rest.entities.City;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class CitiesService {
    @Autowired
    private final DbService dbService;

    public Long addCity(City city) {
        return dbService.addCity(city);
    }

    public List<City> getAllCities() {
        return dbService.getAllCities();
    }

    public List<City> getCitiesByCountry(Long idTara) {
        return dbService.getCitiesByCountry(idTara);
    }

    public boolean updateCity(Long id, City city) {
        return dbService.updateCity(id, city);
    }

    public boolean deleteCity(Long id) {
        return dbService.deleteCity(id);
    }
}
