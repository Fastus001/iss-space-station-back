
package pl.fastus.spacestation.domain.apinow;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApiNow {

    @JsonProperty("iss_position")
    private IssPosition issPosition;

    private String message;
    private Long timestamp;

    @Builder
    public ApiNow(IssPosition issPosition, String message, Long timestamp) {
        this.issPosition = issPosition;
        this.message = message;
        this.timestamp = timestamp;
    }
}
