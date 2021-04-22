package pl.fastus.spacestation.services.jpadata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.fastus.spacestation.domain.Position;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.repositories.StationNowRepository;
import pl.fastus.spacestation.services.StationNowService;

@ExtendWith(MockitoExtension.class)
class StationNowSDJpaServiceImplTest {

    @Mock
    StationNowRepository stationNowRepository;

    @InjectMocks
    StationNowService service;

    StationNow returnStationNow;

    @BeforeEach
    void setUp() {
        Position position = Position.builder()
                .latitude( 25.025 )
                .longitude( -12.025 ).build();

        returnStationNow = StationNow.builder()
                .id( 1L )
                .timeStamp( 125652564L )
                .position(position).build();
    }

//    @Test
//    void findAll() {
//        Set<StationNow> returnStationNow = new HashSet<>();
//        returnStationNow.add( StationNow.builder().id( 1L ).build() );
//        returnStationNow.add( StationNow.builder().id( 2L ).timeStamp( 12453L ).build() );
//
//        when( stationNowRepository.findAll() ).thenReturn( returnStationNow);
//
//        Set<StationNow> findAll = service.findAll();
//
//        assertNotNull( findAll );
//        assertEquals( 2, findAll.size() );
//    }
//
//    @Test
//    void findById() {
//        when( stationNowRepository.findById( anyLong() )).thenReturn( Optional.of( returnStationNow) );
//
//        StationNow stationNow = service.findById( 1L );
//
//        assertNotNull(stationNow);
//    }
//
//    @Test
//    void save() {
//        StationNow toSave = StationNow.builder().id( 1L ).build();
//
//        when( stationNowRepository.save( any() ) ).thenReturn( returnStationNow);
//
//        StationNow stationNowSaved = service.save( toSave );
//
//        assertNotNull(stationNowSaved);
//        verify( stationNowRepository, times( 1 ) ).save( any() );
//    }
//
//    @Test
//    void delete() {
//        service.delete(returnStationNow);
//
//        verify( stationNowRepository, times( 1 ) ).delete( any() );
//    }
//
//    @Test
//    void deleteById() {
//        service.deleteById( 1L );
//
//        verify( stationNowRepository, times( 1 ) ).deleteById( anyLong() );
//    }
}
