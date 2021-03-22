
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


    private String message;
    private Long timestamp;

    @JsonProperty("iss_position")
    private IssPositionDTO issPositionDTO;


    @Builder
    public StationNowDTO(IssPositionDTO issPositionDTO, String message, Long timestamp) {
        this.issPositionDTO = issPositionDTO;
        this.message = message;
        this.timestamp = timestamp;
    }
}
