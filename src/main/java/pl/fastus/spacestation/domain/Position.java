package pl.fastus.spacestation.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "stationNow", callSuper = false)
@ToString(exclude = "stationNow")
@Entity
public class Position extends BaseEntity{

    private double latitude;
    private double longitude;

    @OneToOne(mappedBy = "position")
    private StationNow stationNow;

    @Builder
    public Position(Long id, double latitude, double longitude, StationNow stationNow) {
        super( id );
        this.latitude = latitude;
        this.longitude = longitude;
        this.stationNow = stationNow;
    }
}
