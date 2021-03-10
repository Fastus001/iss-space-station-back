
package pl.fastus.spacestation.domain.apipass;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Response {

    @JsonProperty("risetime")
    private Long riseTime;
    private Integer duration;
}
