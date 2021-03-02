package pl.fastus.spacestation.services.jpadata;

import org.springframework.stereotype.Service;
import pl.fastus.spacestation.domain.IssNow;
import pl.fastus.spacestation.repositories.IssNowRepository;
import pl.fastus.spacestation.repositories.IssPositionRepository;
import pl.fastus.spacestation.services.IssNowService;

import java.util.HashSet;
import java.util.Set;

@Service
public class IssNowSDJpaServiceImpl implements IssNowService {

    private final IssNowRepository issNowRepository;
    private final IssPositionRepository issPositionRepository;

    public IssNowSDJpaServiceImpl(IssNowRepository issNowRepository, IssPositionRepository issPositionRepository) {
        this.issNowRepository = issNowRepository;
        this.issPositionRepository = issPositionRepository;
    }

    @Override
    public Set<IssNow> findAll() {
        Set<IssNow> issNowSet = new HashSet<>();
        issNowRepository.findAll().forEach( issNowSet::add );
        return issNowSet;
    }

    @Override
    public IssNow findById(Long id) {
        return issNowRepository.findById( id ).orElse( null );
    }

    @Override
    public IssNow save(IssNow object) {
        return issNowRepository.save( object );
    }

    @Override
    public void delete(IssNow object) {
        issNowRepository.delete( object );
    }

    @Override
    public void deleteById(Long id) {
        issNowRepository.deleteById( id );
    }
}
