package pl.fastus.spacestation.services.jpadata;

import org.springframework.stereotype.Service;
import pl.fastus.spacestation.domain.IssPosition;
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
    public Set<IssPosition> findAll() {
        Set<IssPosition> positions =  new HashSet<>();
        repository.findAll().forEach( positions::add );
        return positions;
    }

    @Override
    public IssPosition findById(Long id) {
        return repository.findById( id ).orElse( null );
    }

    @Override
    public IssPosition save(IssPosition object) {
        return repository.save( object );
    }

    @Override
    public void delete(IssPosition object) {
        repository.delete( object );
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById( id );
    }
}
