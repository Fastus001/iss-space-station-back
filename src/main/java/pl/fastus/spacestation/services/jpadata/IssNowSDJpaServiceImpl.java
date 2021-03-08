package pl.fastus.spacestation.services.jpadata;

import org.springframework.stereotype.Service;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.repositories.IssNowRepository;
import pl.fastus.spacestation.services.IssNowService;

import java.util.HashSet;
import java.util.Set;

@Service
public class IssNowSDJpaServiceImpl implements IssNowService {

    private final IssNowRepository issNowRepository;

    public IssNowSDJpaServiceImpl(IssNowRepository issNowRepository) {
        this.issNowRepository = issNowRepository;
    }

    @Override
    public Set<StationNow> findAll() {
        Set<StationNow> stationNowSet = new HashSet<>();
        issNowRepository.findAll().forEach( stationNowSet::add );
        return stationNowSet;
    }

    @Override
    public StationNow findById(Long id) {
        return issNowRepository.findById( id ).orElse( null );
    }

    @Override
    public StationNow save(StationNow object) {
        return issNowRepository.save( object );
    }

    @Override
    public void delete(StationNow object) {
        issNowRepository.delete( object );
    }

    @Override
    public void deleteById(Long id) {
        issNowRepository.deleteById( id );
    }
}
