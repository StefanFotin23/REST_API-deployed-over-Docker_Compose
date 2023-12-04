package acs.sprc.rest.entities;

import acs.sprc.rest.utility.TemperatureSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "temperatures", schema = "meteo_data")
@JsonSerialize(using = TemperatureSerializer.class)
@JsonDeserialize
@Entity
public class Temperature {
    @Id
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "id_oras")
    private Long idOras;

    @NotNull
    @Column(name = "valoare")
    private Double valoare;

    @Column(name = "timestamp")
    private Date timestamp = new Date();
}
