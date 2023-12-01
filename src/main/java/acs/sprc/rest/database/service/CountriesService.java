package acs.sprc.rest.database.service;

import acs.sprc.rest.database.repository.CountriesRepository;
import acs.sprc.rest.entities.Country;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class CountriesService {
    @Autowired
    private final CountriesRepository countriesRepository;

    public Long addCountry(Country country) {
        Country savedCountry = countriesRepository.save(country);
        return savedCountry.getId();
    }

    public List<Country> getAllCountries() {
        return countriesRepository.findAll();
    }

    public boolean updateCountry(Long id, Country country) {
        if (countriesRepository.existsById(id)) {
            country.setId(id);
            countriesRepository.save(country);
            return true;
        }
        return false;
    }

    public boolean deleteCountry(Long id) {
        if (countriesRepository.existsById(id)) {
            countriesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
