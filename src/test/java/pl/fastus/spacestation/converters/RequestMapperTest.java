package pl.fastus.spacestation.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.Request;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestMapperTest {
    private static final double LATITUDE = 25.2;
    private static final double LONGITUDE = 12.1;
    private static final int PASSES = 5;
    private static final int ALTITUDE = 1500;
    private static final long DAY_TIME = 123456L;

    RequestMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper( RequestMapper.class );
    }

    @Test
    void toPassesRequest() {
        Request request = Request.builder()
                .latitude( LATITUDE )
                .longitude( LONGITUDE )
                .passes( PASSES )
                .altitude( ALTITUDE )
                .datetime( DAY_TIME ).build();

        final IssPassesRequest converted = mapper.requestToPassesRequest( request );


        assertAll( ()->assertEquals( LATITUDE, converted.getLatitude() ),
                   ()->assertEquals( LONGITUDE, converted.getLongitude()),
                   ()->assertEquals( PASSES, converted.getPasses()),
                   ()->assertEquals( ALTITUDE, converted.getAltitude()),
                   ()->assertEquals( DAY_TIME, converted.getDatetime())
        );
    }
}