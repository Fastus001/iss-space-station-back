package pl.fastus.spacestation.commands;

import lombok.Data;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

import static java.lang.String.valueOf;

@Data
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
    private BigDecimal altitude;

    @Min( 1 )
    @Max( 100 )
    private BigDecimal number;

    public MultiValueMap<String, String> getUriParams(){
        MultiValueMap<String, String> temp = new LinkedMultiValueMap<>();
        temp.put( "lat", List.of( valueOf( latitude ) ) );
        temp.put( "lon", List.of( valueOf( longitude ) ) );

        if(altitude!=null){
            temp.put( "alt", List.of( valueOf( altitude ) ) );
        }
        if ( number !=null ){
            temp.put( "n", List.of( valueOf(number) ) );
        }
        return temp;
    }
}
