-- Create if not exists Database meteo_data
CREATE DATABASE IF NOT EXISTS meteo_data;

-- Switch to the meteo_data database
USE meteo_data;

-- Create City table
CREATE TABLE IF NOT EXISTS meteo_data.cities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_tara INTEGER,
    nume_oras VARCHAR(255) NOT NULL,
    latitudine DOUBLE,
    longitudine DOUBLE,
    CONSTRAINT uc_id_tara_nume_oras UNIQUE (id_tara, nume_oras)
);

-- Create Country table
CREATE TABLE IF NOT EXISTS meteo_data.countries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nume_tara VARCHAR(255) UNIQUE,
    latitudine DOUBLE,
    longitudine DOUBLE
);

-- Create Temperature table
CREATE TABLE IF NOT EXISTS meteo_data.temperatures (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_oras BIGINT,
    valoare VARCHAR(255),
    timestamp TIMESTAMP,
    FOREIGN KEY (id_oras) REFERENCES meteo_data.cities (id),
    UNIQUE (id_oras, timestamp)
);