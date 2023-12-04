package acs.sprc.rest.database.service;

import acs.sprc.rest.database.repository.CountriesRepository;
import acs.sprc.rest.entities.Country;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class CountriesDbService {
    @Autowired
    private final CountriesRepository countriesRepository;
    private final Logger logger = Logger.getLogger("CountriesDbService");

    public Long addCountry(Country country) {
        logger.info("addCountry country " + country.toString());
        try {
            Long id = (long) (getEntitiesCounterFromDb() + 1);
            country.setId(id);
            Country savedCountry = countriesRepository.save(country);
            return savedCountry.getId();
        } catch (Exception e) {
            logger.info(e.toString());
            return 0L;
        }
    }

    public List<Country> getAllCountries() {
        logger.info("getAllCountries");
        return countriesRepository.findAll();
    }

    public boolean updateCountry(Long id, Country country) {
        logger.info("updateCountry id=" + id + " country " + country.toString());
        if (country.getId() == null) {
            return false;
        } else {
            if (!country.getId().equals(id)) {
                return false;
            }
        }

        if (countriesRepository.existsById(id)) {
            country.setId(id);
            countriesRepository.save(country);
            return true;
        }
        return false;
    }

    public boolean deleteCountry(Long id) {
        logger.info("deleteCountry id=" + id);
        if (countriesRepository.existsById(id)) {
            countriesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private int getEntitiesCounterFromDb() {
        return getAllCountries().size();
    }
}
