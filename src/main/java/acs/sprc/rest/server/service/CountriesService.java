package acs.sprc.rest.server.service;

import acs.sprc.rest.database.DbService;
import acs.sprc.rest.entities.Country;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class CountriesService {
    @Autowired
    private final DbService dbService;

    public Long addCountry(Country country) {
        return dbService.addCountry(country);
    }

    public List<Country> getAllCountries() {
        return dbService.getAllCountries();
    }

    public boolean updateCountry(Long id, Country country) {
        return dbService.updateCountry(id, country);
    }

    public boolean deleteCountry(Long id) {
        return dbService.deleteCountry(id);
    }
}
