package pl.fastus.spacestation.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Data
@Entity
public class StationNow extends BaseEntity{

    private double latitude;
    private double longitude;
    private Long timeStamp;

    @Builder
    public StationNow(Long id, double latitude, double longitude, Long timeStamp) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeStamp = timeStamp;
    }
}

