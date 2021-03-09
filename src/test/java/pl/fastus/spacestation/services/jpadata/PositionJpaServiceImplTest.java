package pl.fastus.spacestation.services.jpadata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.fastus.spacestation.domain.Position;
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
class PositionJpaServiceImplTest {

    @Mock
    IssPositionRepository repository;

    @InjectMocks
    IssPositionJpaServiceImpl service;

    Position returnPosition;


    @BeforeEach
    void setUp() {
        returnPosition = Position.builder().id( 1L ).build();
    }

    @Test
    void findAll() {
        Set<Position> returnPositions = new HashSet<>();
        returnPositions.add( Position.builder().id( 1L ).build() );
        returnPositions.add( Position.builder().id( 2L ).latitude( 25.3 ).build() );

        when(repository.findAll()).thenReturn(returnPositions);

        final Set<Position> positions = service.findAll();

        assertNotNull( positions );
        assertEquals( 2, positions.size() );
    }

    @Test
    void findById() {
        when( repository.findById( anyLong() ) ).thenReturn( Optional.of( returnPosition ));

        final Position byId = service.findById( 1L );

        assertNotNull( byId );
    }

    @Test
    void save() {
        Position toSave = Position.builder().id( 1L ).build();

        when( repository.save( any() ) ).thenReturn( returnPosition );

        final Position savedPosition = service.save( toSave );

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