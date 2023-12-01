package acs.sprc.rest.database.repository;

import acs.sprc.rest.entities.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperaturesRepository extends JpaRepository<Temperature, Long> {}
