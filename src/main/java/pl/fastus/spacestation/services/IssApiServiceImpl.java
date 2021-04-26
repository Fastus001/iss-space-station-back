package pl.fastus.spacestation.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import pl.fastus.spacestation.domain.dto.StationPassesRequestDTO;
import reactor.core.publisher.Mono;

@Service
public class IssApiServiceImpl implements IssApiService {
    private final String apiPass;

    public IssApiServiceImpl(@Value("${api.pass}") String apiPass) {
        this.apiPass = apiPass;
    }

    @Override
    public Mono<StationPassesRequestDTO> createPassesRequest(MultiValueMap<String, String> params){
        return WebClient
                .create(apiPass)
                .get()
                .uri( uri->uri.queryParams( params ).build(  ) )
                .accept( MediaType.APPLICATION_JSON )
                .exchangeToMono( response->response.bodyToMono( StationPassesRequestDTO.class ) );
    }
}
