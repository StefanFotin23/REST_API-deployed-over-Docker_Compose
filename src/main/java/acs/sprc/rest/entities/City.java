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
@Table(name = "cities", schema = "meteo_data", uniqueConstraints = @UniqueConstraint(columnNames = {"id_tara" , "nume_oras"}))
@JsonSerialize
@JsonDeserialize
@Entity
public class City {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_tara")
    private Long idTara;

    @Column(name = "nume_oras")
    private String nume;

    @Column(name = "latitudine")
    private Double lat;

    @Column(name = "longitudine")
    private Double lon;
}
