package pl.fastus.spacestation.domain.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class PassesRequestDTO {

    @DecimalMin( value = "-80.0")
    @DecimalMax( value = "80.0" )
    @NotNull(message = "Object have to have NotNull longitude ")
    private BigDecimal latitude;

    @DecimalMin( value = "-180.0")
    @DecimalMax( value = "180.0" )
    @NotNull(message = "Object have to have NotNull longitude ")
    private BigDecimal longitude;

    @Min( 0 )
    @Max( 10000 )
    private BigDecimal altitude;

    @Min( 1 )
    @Max( 100 )
    private BigDecimal number;
}
