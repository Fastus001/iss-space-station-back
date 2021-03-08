package pl.fastus.spacestation.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "stationNow")
@ToString(exclude = "stationNow")
@Entity
public class Position {

    @Id
    @GeneratedValue
    private Long id;

    private double latitude;
    private double longitude;

    @OneToOne(mappedBy = "position")
    private StationNow stationNow;
}
