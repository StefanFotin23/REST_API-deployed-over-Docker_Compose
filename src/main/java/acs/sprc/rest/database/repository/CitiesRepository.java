package acs.sprc.rest.database.repository;

import acs.sprc.rest.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends JpaRepository<City, Long> {}
