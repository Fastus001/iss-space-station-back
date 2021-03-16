
package pl.fastus.spacestation.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class StationPassesRequestDTO {

    private String message;
    private RequestDTO requestDTO;
    private List<ResponseDTO> responseDTO = null;
}
