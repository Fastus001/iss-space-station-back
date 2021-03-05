package pl.fastus.spacestation.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PassTimesCommand {

    @DecimalMin( value = "-80.0")
    @DecimalMax( value = "80.0" )
    @NotNull
    private BigDecimal latitude;

    @DecimalMin( value = "-180.0")
    @DecimalMax( value = "180.0" )
    @NotNull
    private BigDecimal longitude;

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
