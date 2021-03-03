package pl.fastus.spacestation.services.jpadata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.fastus.spacestation.domain.IssPosition;
import pl.fastus.spacestation.repositories.IssPositionRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IssPositionJpaServiceImplTest {

    @Mock
    IssPositionRepository repository;

    @InjectMocks
    IssPositionJpaServiceImpl service;

    IssPosition returnPosition;


    @BeforeEach
    void setUp() {
        returnPosition = IssPosition.builder().id( 1L ).build();
    }

    @Test
    void findAll() {
        Set<IssPosition> returnIssPositions = new HashSet<>();
        returnIssPositions.add( IssPosition.builder().id( 1L ).build() );
        returnIssPositions.add( IssPosition.builder().id( 2L ).build() );

        when(repository.findAll()).thenReturn( returnIssPositions );

        final Set<IssPosition> positions = service.findAll();

        assertNotNull( positions );
        assertEquals( 2, positions.size() );
    }

    @Test
    void findById() {
        when( repository.findById( anyLong() ) ).thenReturn( Optional.of( returnPosition ));

        final IssPosition byId = service.findById( 1L );

        assertNotNull( byId );
    }

    @Test
    void save() {
        IssPosition toSave = IssPosition.builder().id( 1L ).build();

        when( repository.save( any() ) ).thenReturn( returnPosition );

        final IssPosition savedPosition = service.save( toSave );

        assertNotNull( savedPosition );
        verify( repository, times( 1 )).save( any() );
    }

    @Test
    void delete() {
        service.delete( returnPosition );

        verify( repository,times( 1 ) ).delete( any() );
    }

    @Test
    void deleteById() {
        service.deleteById( 1L );

        verify( repository,times( 1 ) ).deleteById( anyLong() );
    }
}