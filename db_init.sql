-- Create if not exists Database meteo_data
CREATE DATABASE IF NOT EXISTS meteo_data;

-- Switch to the meteo_data database
USE meteo_data;

-- Create Country table
CREATE TABLE IF NOT EXISTS meteo_data.countries (
    id BIGINT PRIMARY KEY,
    nume_tara VARCHAR(255) NOT NULL UNIQUE,
    latitudine DOUBLE,
    longitudine DOUBLE
);

-- Create City table
CREATE TABLE IF NOT EXISTS meteo_data.cities (
    id BIGINT PRIMARY KEY,
    id_tara BIGINT NOT NULL,
    nume_oras VARCHAR(255) NOT NULL UNIQUE,
    latitudine DOUBLE,
    longitudine DOUBLE,
    FOREIGN KEY (id_tara) REFERENCES meteo_data.countries (id)
);

-- Create Temperature table
CREATE TABLE IF NOT EXISTS meteo_data.temperatures (
    id BIGINT PRIMARY KEY,
    id_oras BIGINT NOT NULL,
    valoare VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP,
    FOREIGN KEY (id_oras) REFERENCES meteo_data.cities (id)
);