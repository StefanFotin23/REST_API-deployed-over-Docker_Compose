package acs.sprc.rest.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "countries", schema = "meteo_data", uniqueConstraints = @UniqueConstraint(columnNames = "nume_tara"))
@JsonSerialize
@JsonDeserialize
@Entity
public class Country {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nume_tara")
    private String nume;

    @Column(name = "latitudine")
    private Double lat;

    @Column(name = "longitudine")
    private Double lon;
}
