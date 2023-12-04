package acs.sprc.rest.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "temperatures", schema = "meteo_data", uniqueConstraints = @UniqueConstraint(columnNames = {"id_oras" , "timestamp"}))
@JsonSerialize
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
    private String timestamp;

    public Temperature(Long id, Long idOras, Double valoare, String timestamp) {
        if (id != null) {
            this.id = id;
        }
        this.idOras = idOras;
        this.valoare = valoare;
        if (timestamp != null) {
            this.timestamp = timestamp;
        } else {
            this.timestamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }
    }
}
