package pl.fastus.spacestation.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.dto.StationNowDTO;
import pl.fastus.spacestation.mappers.StationMapper;
import pl.fastus.spacestation.repositories.StationNowRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class StationNowServiceImpl implements StationNowService{

    private final StationNowRepository repository;

    @Override
    public StationNow save(StationNowDTO stationNowDTO) {
        StationNow stationNow = StationMapper.INSTANCE.stationNowDTOtoStationNow(stationNowDTO);
        return repository.save(stationNow);
    }
}
