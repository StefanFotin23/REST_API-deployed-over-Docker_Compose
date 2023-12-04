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
@Table(name = "countries", schema = "meteo_data", uniqueConstraints = @UniqueConstraint(columnNames = "nume_tara"))
@JsonSerialize
@JsonDeserialize
@Entity
public class Country {
    @Id
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nume_tara")
    private String nume;

    @NotNull
    @Column(name = "latitudine")
    private Double lat;

    @NotNull
    @Column(name = "longitudine")
    private Double lon;
}
