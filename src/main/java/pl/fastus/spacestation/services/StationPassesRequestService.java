package pl.fastus.spacestation.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.dto.StationPassesRequestDTO;
import pl.fastus.spacestation.mappers.IssPassesMapper;
import pl.fastus.spacestation.repositories.StationPassesRequestRepository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StationPassesRequestService {

    private final StationPassesRequestRepository repository;
    private final IssPassesMapper passesMapper;

    public StationPassesRequestDTO saveStationPassRequest(StationPassesRequestDTO toSave) {
        IssPassesRequest issPassesRequest = passesMapper.toIssPassesRequest(toSave);

        IssPassesRequest issPassesSaved = repository.save(issPassesRequest);

        return passesMapper.issPassesRequestToStationPassesRequestDTO(issPassesSaved);
    }

    public StationPassesRequestDTO findStationPassRequestById(Long id) {
        Optional<IssPassesRequest> optionalIssPasses = repository.findById(id);
        if(optionalIssPasses.isEmpty()){
            throw new IllegalArgumentException();
        }
        IssPassesRequest request = optionalIssPasses.get();
        return passesMapper.issPassesRequestToStationPassesRequestDTO(request);
    }
}
