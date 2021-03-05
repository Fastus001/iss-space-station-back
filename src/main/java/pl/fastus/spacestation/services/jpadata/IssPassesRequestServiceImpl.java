package pl.fastus.spacestation.services.jpadata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.repositories.IssPassesRequestRepository;
import pl.fastus.spacestation.services.IssPassesRequestService;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class IssPassesRequestServiceImpl implements IssPassesRequestService {

    private final IssPassesRequestRepository repository;

    public IssPassesRequestServiceImpl(IssPassesRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<IssPassesRequest> findAll() {
        Set<IssPassesRequest> requests = new HashSet<>();
        repository.findAll().forEach( requests::add );
        return requests;
    }

    @Override
    public IssPassesRequest findById(Long id) {
        return repository.findById( id ).orElse( null );
    }

    @Override
    public IssPassesRequest save(IssPassesRequest object) {
        return repository.save( object );
    }

    @Override
    public void delete(IssPassesRequest object) {
        repository.delete( object );
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById( id );
    }
}
