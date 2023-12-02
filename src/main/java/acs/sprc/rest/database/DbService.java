package acs.sprc.rest.database;

import acs.sprc.rest.database.service.CitiesDbService;
import acs.sprc.rest.database.service.CountriesDbService;
import acs.sprc.rest.database.service.TemperaturesDbService;
import acs.sprc.rest.entities.City;
import acs.sprc.rest.entities.Country;
import acs.sprc.rest.entities.Temperature;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class DbService {
    private final DbConfig config;
    private final CitiesDbService citiesService;
    private final CountriesDbService countriesService;
    private final TemperaturesDbService temperaturesService;

    // COUNTRY
    public Long addCountry(Country country) {
        return countriesService.addCountry(country);
    }

    public List<Country> getAllCountries() {
        return countriesService.getAllCountries();
    }

    public boolean updateCountry(Long id, Country country) {
        return countriesService.updateCountry(id, country);
    }

    public boolean deleteCountry(Long id) {
        return countriesService.deleteCountry(id);
    }

    // CITY
    public Long addCity(City city) {
        return citiesService.addCity(city);
    }

    public List<City> getAllCities() {
        return citiesService.getAllCities();
    }

    public List<City> getCitiesByCountry(Long idTara) {
        return citiesService.getCitiesByCountry(idTara);
    }

    public boolean updateCity(Long id, City city) {
        return citiesService.updateCity(id, city);
    }

    public boolean deleteCity(Long id) {
        return citiesService.deleteCity(id);
    }

    // TEMPERATURE
    public Long addTemperature(Temperature temperature) {
        return temperaturesService.addTemperature(temperature);
    }

    public List<Temperature> getTemperatures(Double lat, Double lon, Date from, Date until) {
        return temperaturesService.getTemperatures(lat, lon, from, until);
    }

    public List<Temperature> getTemperaturesByCity(Long idOras, Date from, Date until) {
        return temperaturesService.getTemperaturesByCity(idOras, from, until);
    }

    public List<Temperature> getTemperaturesByCountry(Long idTara, Date from, Date until) {
        return temperaturesService.getTemperaturesByCountry(idTara, from, until);
    }

    public boolean updateTemperature(Long id, Temperature temperature) {
        return temperaturesService.updateTemperature(id, temperature);
    }

    public boolean deleteTemperature(Long id) {
        return temperaturesService.deleteTemperature(id);
    }
}
