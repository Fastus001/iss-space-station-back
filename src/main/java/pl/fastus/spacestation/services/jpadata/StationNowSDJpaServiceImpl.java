package pl.fastus.spacestation.services.jpadata;

import org.springframework.stereotype.Service;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.repositories.StationNowRepository;
import pl.fastus.spacestation.services.StationNowService;

import java.util.HashSet;
import java.util.Set;

@Service
public class StationNowSDJpaServiceImpl implements StationNowService {

    private final StationNowRepository stationNowRepository;

    public StationNowSDJpaServiceImpl(StationNowRepository stationNowRepository) {
        this.stationNowRepository = stationNowRepository;
    }

    @Override
    public Set<StationNow> findAll() {
        Set<StationNow> stationNowSet = new HashSet<>();
        stationNowRepository.findAll().forEach( stationNowSet::add );
        return stationNowSet;
    }

    @Override
    public StationNow findById(Long id) {
        return stationNowRepository.findById( id ).orElse( null );
    }

    @Override
    public StationNow save(StationNow object) {
        return stationNowRepository.save( object );
    }

    @Override
    public void delete(StationNow object) {
        stationNowRepository.delete( object );
    }

    @Override
    public void deleteById(Long id) {
        stationNowRepository.deleteById( id );
    }
}
