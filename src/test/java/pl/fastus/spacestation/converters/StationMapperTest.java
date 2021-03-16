package pl.fastus.spacestation.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.dto.IssPosition;
import pl.fastus.spacestation.domain.dto.StationNowDTO;

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
    void convertFromApiNowTest(){
        IssPosition issPosition = IssPosition.builder()
                .latitude( LATITUDE )
                .longitude( LONGITUDE ).build();

        StationNowDTO stationNowDTO = StationNowDTO.builder()
                .message( "message" )
                .timestamp( DAY_TIME )
                .issPosition( issPosition ).build();

        final StationNow stationNow = mapper.stationNowDTOtoStationNow( stationNowDTO );

        assertAll( ()->assertEquals( DAY_TIME, stationNow.getTimeStamp() ),
                   ()->assertEquals( LONGITUDE, stationNow.getPosition().getLongitude()),
                   ()->assertEquals( LATITUDE, stationNow.getPosition().getLatitude())
        );
    }
}