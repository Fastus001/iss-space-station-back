package pl.fastus.spacestation.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PassTimesCommand {

    private double latitude;
    private double longitude;
    private int altitude;
    private int numberOfPasses;
}
