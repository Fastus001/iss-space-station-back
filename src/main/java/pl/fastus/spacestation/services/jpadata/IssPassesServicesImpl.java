package pl.fastus.spacestation.services.jpadata;

import org.springframework.stereotype.Service;
import pl.fastus.spacestation.domain.IssPasses;
import pl.fastus.spacestation.repositories.IssPassesRepository;
import pl.fastus.spacestation.services.IssPassesService;

import java.util.HashSet;
import java.util.Set;

@Service
public class IssPassesServicesImpl implements IssPassesService {

    private final IssPassesRepository repository;

    public IssPassesServicesImpl(IssPassesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<IssPasses> findAll() {
        Set<IssPasses> passes = new HashSet<>();
        repository.findAll().forEach( passes::add );
        return passes;
    }

    @Override
    public IssPasses findById(Long id) {
        return repository.findById( id ).orElse( null );
    }

    @Override
    public IssPasses save(IssPasses object) {
        return repository.save( object );
    }

    @Override
    public void delete(IssPasses object) {
        repository.delete( object );
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById( id );
    }
}
