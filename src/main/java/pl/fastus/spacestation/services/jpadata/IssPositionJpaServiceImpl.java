package pl.fastus.spacestation.services.jpadata;

import org.springframework.stereotype.Service;
import pl.fastus.spacestation.domain.Position;
import pl.fastus.spacestation.repositories.IssPositionRepository;
import pl.fastus.spacestation.services.IssPositionService;

import java.util.HashSet;
import java.util.Set;

@Service
public class IssPositionJpaServiceImpl implements IssPositionService {

    private final IssPositionRepository repository;

    public IssPositionJpaServiceImpl(IssPositionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Position> findAll() {
        Set<Position> positions =  new HashSet<>();
        repository.findAll().forEach( positions::add );
        return positions;
    }

    @Override
    public Position findById(Long id) {
        return repository.findById( id ).orElse( null );
    }

    @Override
    public Position save(Position object) {
        return repository.save( object );
    }

    @Override
    public void delete(Position object) {
        repository.delete( object );
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById( id );
    }
}
