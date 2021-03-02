package pl.fastus.spacestation.services.jpadata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.fastus.spacestation.domain.IssNow;
import pl.fastus.spacestation.domain.IssPosition;
import pl.fastus.spacestation.repositories.IssNowRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IssNowSDJpaServiceImplTest {

    @Mock
    IssNowRepository issNowRepository;

    @InjectMocks
    IssNowSDJpaServiceImpl service;

    IssNow returnIssNow;

    @BeforeEach
    void setUp() {
        IssPosition issPosition = IssPosition.builder()
                .latitude( 25.025 )
                .longitude( -12.025 ).build();

        returnIssNow = IssNow.builder()
                .id( 1L )
                .timeStamp( 125652564L )
                .issPosition( issPosition ).build();
    }

    @Test
    void findAll() {
        Set<IssNow> returnIssNow = new HashSet<>();
        returnIssNow.add( IssNow.builder().id( 1L ).build() );
        returnIssNow.add( IssNow.builder().id( 2L ).build() );

        when( issNowRepository.findAll() ).thenReturn( returnIssNow );

        Set<IssNow> findAll = service.findAll();

        assertNotNull( findAll );
        assertEquals( 2, findAll.size() );
    }

    @Test
    void findById() {
        when(issNowRepository.findById( anyLong() )).thenReturn( Optional.of( returnIssNow ) );

        IssNow issNow = service.findById( 1L );

        assertNotNull(issNow);
    }

    @Test
    void save() {
        IssNow toSave = IssNow.builder().id( 1L ).build();

        when( issNowRepository.save( any() ) ).thenReturn( returnIssNow );

        IssNow issNowSaved = service.save( toSave );

        assertNotNull(issNowSaved);
        verify( issNowRepository,times( 1 ) ).save( any() );
    }

    @Test
    void delete() {
        service.delete( returnIssNow );

        verify( issNowRepository,times( 1 ) ).delete( any() );
    }

    @Test
    void deleteById() {
        service.deleteById( 1L );

        verify( issNowRepository,times( 1 ) ).deleteById( anyLong() );
    }
}