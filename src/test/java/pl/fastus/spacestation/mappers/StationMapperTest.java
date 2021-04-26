package pl.fastus.spacestation.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.dto.IssPositionDTO;
import pl.fastus.spacestation.domain.dto.StationNowDTO;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StationMapperTest {
    private static final double LATITUDE = 25.2;
    private static final double LONGITUDE = 12.1;
    private static final long DAY_TIME = 123456L;

    StationMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper( StationMapper.class );
    }

    @Test
    void convertFromApiNowTest(){
        IssPositionDTO issPositionDTO = IssPositionDTO.builder()
                .latitude( LATITUDE )
                .longitude( LONGITUDE ).build();

        StationNowDTO stationNowDTO = StationNowDTO.builder()
                .message( "message" )
                .timestamp( DAY_TIME )
                .issPositionDTO( issPositionDTO ).build();

        final StationNow stationNow = mapper.stationNowDTOtoStationNow( stationNowDTO );

        assertAll( ()->assertEquals( DAY_TIME, stationNow.getTimeStamp() ),
                   ()->assertEquals( LONGITUDE, stationNow.getLongitude()),
                   ()->assertEquals( LATITUDE, stationNow.getLatitude())
        );
    }
}
