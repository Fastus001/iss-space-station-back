package pl.fastus.spacestation.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.dto.PassesRequestDTO;
import pl.fastus.spacestation.domain.dto.StationPassesRequestDTO;
import pl.fastus.spacestation.mappers.IssPassesMapper;
import pl.fastus.spacestation.mappers.PassesRequestParamMapper;
import pl.fastus.spacestation.repositories.IssPassesRequestRepository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssPassesRequestServiceImpl implements IssPassesRequestService {

    private final IssPassesRequestRepository repository;
    private final IssApiService apiService;
    private final IssPassesMapper issPassesMapper;

    @Override
    public StationPassesRequestDTO saveDTO(PassesRequestDTO toSave) {
        StationPassesRequestDTO callback = apiService.createPassesRequest(PassesRequestParamMapper
                .getUriParams(toSave)).block();
        assert callback != null;
        IssPassesRequest issPassesRequest = issPassesMapper.toIssPassesRequest(callback.getRequestDTO(), callback.getResponseDTO());
        IssPassesRequest issPassesSaved = repository.save(issPassesRequest);
        return issPassesMapper.IssPassesRequestToStationPassesRequestDTO(issPassesSaved);
    }

    @Override
    public StationPassesRequestDTO findById(Long id) {
        Optional<IssPassesRequest> optionalIssPasses = repository.findById(id);
        if(optionalIssPasses.isEmpty()){
            throw new IllegalArgumentException();
        }
        IssPassesRequest request = optionalIssPasses.get();
        return issPassesMapper.IssPassesRequestToStationPassesRequestDTO(request);
    }
}
