
package pl.fastus.spacestation.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class StationPassesRequestDTO {

    private String message;

    @JsonProperty("request")
    private RequestDTO requestDTO;

    @JsonProperty("response")
    private List<ResponseDTO> responseDTO = null;
}
