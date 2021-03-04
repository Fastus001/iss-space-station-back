package pl.fastus.spacestation.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PassTimesCommand {

    @Min( -80 )
    @Max( 80 )
    @NotEmpty
    private double latitude;

    @Min( -180 )
    @Max( 180 )
    @NotEmpty
    private double longitude;

    @Min( 0 )
    @Max( 10000 )
    private int altitude;

    @Min( 1 )
    @Max( 100 )
    private int numberOfPasses;
}
