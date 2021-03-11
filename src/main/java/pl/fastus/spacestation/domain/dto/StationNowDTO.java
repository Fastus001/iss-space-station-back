
package pl.fastus.spacestation.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class StationNowDTO {

    @JsonProperty("iss_position")
    private IssPosition issPosition;

    private String message;
    private Long timestamp;

    @Builder
    public StationNowDTO(IssPosition issPosition, String message, Long timestamp) {
        this.issPosition = issPosition;
        this.message = message;
        this.timestamp = timestamp;
    }
}
