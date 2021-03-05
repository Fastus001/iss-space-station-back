
package pl.fastus.spacestation.domain;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
public class Request {

    private Integer altitude;
    private Integer datetime;
    private Double latitude;
    private Double longitude;
    private Integer passes;
}
