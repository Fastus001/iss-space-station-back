
package pl.fastus.spacestation.domain.apinow;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class IssPosition {

    private Double latitude;
    private Double longitude;
}
