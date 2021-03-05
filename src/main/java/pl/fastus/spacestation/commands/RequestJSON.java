package pl.fastus.spacestation.commands;

import lombok.Data;

@Data
public class RequestJSON {

    private int altitude;
    private long datetime;
    private double latitude;
    private double longitude;
    private int passes;
}
