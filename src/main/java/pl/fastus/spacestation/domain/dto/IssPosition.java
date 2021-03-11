
package pl.fastus.spacestation.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class IssPosition {

    private Double latitude;
    private Double longitude;

    @Builder
    public IssPosition(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
