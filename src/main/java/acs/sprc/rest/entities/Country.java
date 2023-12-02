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
@Table(name = "Countries", schema = "meteo_data", uniqueConstraints = @UniqueConstraint(columnNames = "nume_tara"))
@JsonSerialize
@JsonDeserialize
@Entity
public class Country {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("nume")
    @Column(name = "nume_tara")
    private String nume_tara;

    @Column(name = "lat")
    private Double latitudine;

    @Column(name = "lon")
    private Double longitudine;
}
