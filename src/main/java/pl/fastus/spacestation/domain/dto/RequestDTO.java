
package pl.fastus.spacestation.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RequestDTO {

    private Double latitude;
    private Double longitude;
    private Integer altitude;
    private Integer passes;

    @JsonProperty("datetime")
    private Long dateTime;

    @Builder
    public RequestDTO(Double latitude, Double longitude, Integer altitude, Integer passes, Long dateTime) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.passes = passes;
        this.dateTime = dateTime;
    }
}
