package acs.sprc.rest.database;

import acs.sprc.rest.database.service.CitiesService;
import acs.sprc.rest.database.service.CountriesService;
import acs.sprc.rest.database.service.TemperaturesService;
import acs.sprc.rest.entities.City;
import acs.sprc.rest.entities.Country;
import acs.sprc.rest.entities.Temperature;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class DbService {
    private final CitiesService citiesService;
    private final CountriesService countriesService;
    private final TemperaturesService temperaturesService;

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
