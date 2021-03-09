
package pl.fastus.spacestation.domain;

import lombok.Data;

@Data
public class Request {

    private Integer altitude;
    private Long datetime;
    private Double latitude;
    private Double longitude;
    private Integer passes;
}
