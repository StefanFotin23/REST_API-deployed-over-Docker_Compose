package acs.sprc.rest.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
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
    private LocalDate timestamp = LocalDate.now();
}
