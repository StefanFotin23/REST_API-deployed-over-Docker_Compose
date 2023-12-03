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
import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class DbService {
    private final DbConfig config;
    private final CitiesDbService citiesService;
    private final CountriesDbService countriesService;
    private final TemperaturesDbService temperaturesService;
    private final Logger logger = Logger.getLogger("DbService");

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
        logger.info("getTemperatures lat=" + lat + " lon=" + lon + " from=" + from + " until=" + until);
        List<City> cities = citiesService.getAllCities();
        for (City city : cities) {
            // Iterate through all cities
            if (lat != null) {
                if (city.getLat() != null) {
                    if (!city.getLat().equals(lat)) {
                        // Remove the city that is not in the correct coordinates
                        cities.remove(city);
                    }
                } else {
                    cities.remove(city);
                }
            }
            if (lon != null) {
                if (city.getLon() != null) {
                    if (!city.getLon().equals(lon)) {
                        // Remove the city that is not in the correct coordinates
                        cities.remove(city);
                    }
                } else {
                    cities.remove(city);
                }
            }
        }
        return temperaturesService.getTemperaturesByDate(from, until, getTemperaturesFromCities(cities));
    }

    public List<Temperature> getTemperaturesByCity(Long idOras, Date from, Date until) {
        return temperaturesService.getTemperaturesByCity(idOras, from, until);
    }

    public List<Temperature> getTemperaturesByCountry(Long idTara, Date from, Date until) {
        logger.info("getTemperaturesByCountry idTara=" + idTara + " from=" + from + " until=" + until);
        List<City> cities = citiesService.getAllCities();
        for (City city : cities) {
            // Iterate through all cities
            if (city.getIdTara() != null) {
                if (!city.getIdTara().equals(idTara)) {
                    // Remove the city that is not in the correct country
                    cities.remove(city);
                }
            } else {
                // if the city has idTara null, we remove it
                cities.remove(city);
            }
        }
        return temperaturesService.getTemperaturesByDate(from, until, getTemperaturesFromCities(cities));
    }

    public boolean updateTemperature(Long id, Temperature temperature) {
        return temperaturesService.updateTemperature(id, temperature);
    }

    public boolean deleteTemperature(Long id) {
        return temperaturesService.deleteTemperature(id);
    }

    public List<Temperature> getTemperaturesFromCities(List<City> cities) {
        List<Temperature> temperatures = temperaturesService.getAllTemperatures();
        for (Temperature temperature : temperatures) {
            // Iterate through all temperatures
            boolean idFound = false;
            for (City city : cities) {
                // Iterate through all cities
                if (temperature.getIdOras() != null) {
                    if (temperature.getIdOras().equals(city.getId())) {
                        // if the temperature's cityId is found
                        idFound = true;
                        break;
                    }
                } else {
                    // if temperature has null city, we remove it
                    temperatures.remove(temperature);
                    break;
                }
            }
            if (!idFound) {
                // Remove the temperature that is not from a city with correct coordinates
                temperatures.remove(temperature);
            }
        }
        return temperatures;
    }
}
