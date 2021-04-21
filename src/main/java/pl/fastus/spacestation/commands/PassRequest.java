package pl.fastus.spacestation.commands;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class PassRequest {

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
}
