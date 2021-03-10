package pl.fastus.spacestation.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.apinow.ApiNow;
import pl.fastus.spacestation.domain.apinow.IssPosition;
import pl.fastus.spacestation.domain.apipass.Request;
import pl.fastus.spacestation.domain.apipass.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StationMapperTest {
    private static final double LATITUDE = 25.2;
    private static final double LONGITUDE = 12.1;
    private static final int PASSES = 5;
    private static final int ALTITUDE = 1500;
    private static final long DAY_TIME = 123456L;

    StationMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper( StationMapper.class );
    }

    @Test
    void toPassesRequest() {
        Request request = Request.builder()
                .latitude( LATITUDE )
                .longitude( LONGITUDE )
                .passes( PASSES )
                .altitude( ALTITUDE )
                .datetime( DAY_TIME ).build();
        List<Response> responses = new ArrayList<>();

        Response response = new Response();
        response.setDuration( 10 );
        response.setRiseTime( 12356L );
        responses.add( response );


        final IssPassesRequest converted = mapper.requestToPassesRequest( request, responses );
        converted.getResponses().forEach( System.out::println );


        assertAll( ()->assertEquals( LATITUDE, converted.getLatitude() ),
                   ()->assertEquals( LONGITUDE, converted.getLongitude()),
                   ()->assertEquals( PASSES, converted.getPasses()),
                   ()->assertEquals( ALTITUDE, converted.getAltitude()),
                   ()->assertEquals( DAY_TIME, converted.getDatetime())
        );
    }


    @Test
    void convertFromApiNowTest(){
        IssPosition issPosition = IssPosition.builder()
                .latitude( LATITUDE )
                .longitude( LONGITUDE ).build();

        ApiNow apiNow = ApiNow.builder()
                .message( "message" )
                .timestamp( DAY_TIME )
                .issPosition( issPosition ).build();

        final StationNow stationNow = mapper.convertFromApiNow( apiNow );

        assertAll( ()->assertEquals( DAY_TIME, stationNow.getTimeStamp() ),
                   ()->assertEquals( LONGITUDE, stationNow.getPosition().getLongitude()),
                   ()->assertEquals( LATITUDE, stationNow.getPosition().getLatitude())
        );
    }
}