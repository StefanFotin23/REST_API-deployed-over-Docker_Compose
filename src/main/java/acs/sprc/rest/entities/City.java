package acs.sprc.rest.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;

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
    private Long id;

    @NotNull
    @Column(name = "id_tara")
    private Long idTara;

    @NotNull
    @Column(name = "nume_oras")
    private String nume;

    @NotNull
    @Column(name = "latitudine")
    private Double lat;

    @NotNull
    @Column(name = "longitudine")
    private Double lon;
}
