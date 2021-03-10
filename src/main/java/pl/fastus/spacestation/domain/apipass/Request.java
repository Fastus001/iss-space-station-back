
package pl.fastus.spacestation.domain.apipass;

import lombok.Builder;
import lombok.Data;

@Data
public class Request {

    private Double latitude;
    private Double longitude;
    private Integer altitude;
    private Integer passes;
    private Long datetime;

    @Builder
    public Request(Double latitude, Double longitude, Integer altitude, Integer passes, Long datetime) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.passes = passes;
        this.datetime = datetime;
    }
}
