package pl.fastus.spacestation.services;

import org.springframework.util.MultiValueMap;
import pl.fastus.spacestation.domain.dto.AstronautsDTO;
import pl.fastus.spacestation.domain.dto.StationPassesRequestDTO;
import reactor.core.publisher.Mono;

public interface IssApiService{

     Mono<StationPassesRequestDTO> createPassesRequest(MultiValueMap<String, String> params);

     AstronautsDTO getAstronauts();
}
