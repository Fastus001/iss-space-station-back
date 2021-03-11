
package pl.fastus.spacestation.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Response {

    @JsonProperty("risetime")
    private Long riseTime;
    private Integer duration;
}
