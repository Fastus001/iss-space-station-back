package pl.fastus.spacestation.json;

import lombok.Data;

@Data
public class RequestJSON {

    private int altitude;
    private long datetime;
    private double latitude;
    private double longitude;
    private int passes;
}
