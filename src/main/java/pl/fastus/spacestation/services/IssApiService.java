package pl.fastus.spacestation.services;

import org.springframework.util.MultiValueMap;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.dto.AstronautsDTO;
import reactor.core.publisher.Mono;

public interface IssApiService {

     Mono<IssPassesRequest> createIssPassesRequest(MultiValueMap<String, String> params);

     AstronautsDTO getAstronauts();
}
