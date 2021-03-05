package pl.fastus.spacestation.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PassTimesCommand {

    @Min( -80 )
    @Max( 80 )
    @NotNull
    private Integer latitude;

    @Min( -180 )
    @Max( 180 )
    @NotNull
    private Integer longitude;

    @Min( 0 )
    @Max( 10000 )
    private Integer altitude;

    @Min( 1 )
    @Max( 100 )
    private Integer numberOfPasses;

    public String getUrl(){
        var sb = new StringBuilder("?lat="+latitude+"&lon="+longitude);
        if(altitude!=null){
            sb.append( "&alt=" ).append( altitude );
        }
        if ( numberOfPasses !=null ){
            sb.append( "&n=" ).append( numberOfPasses );
        }
        return sb.toString();
    }
}
