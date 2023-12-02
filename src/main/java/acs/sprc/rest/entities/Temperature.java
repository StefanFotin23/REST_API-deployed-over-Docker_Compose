package acs.sprc.rest.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Temperatures", schema = "meteo_data", uniqueConstraints = @UniqueConstraint(columnNames = {"id_oras" , "timestamp"}))
@JsonSerialize
@JsonDeserialize
@Entity
public class Temperature {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("idOras")
    @Column(name = "id_oras")
    private Long id_oras;

    @JsonProperty("valoare")
    @Column(name = "valoare")
    private String valoare;

    @Column(name = "timestamp")
    private Date timestamp = new Date();
}
