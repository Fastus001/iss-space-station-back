package pl.fastus.spacestation.services;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.dto.AstronautsDTO;
import pl.fastus.spacestation.domain.dto.StationPassesRequestDTO;
import pl.fastus.spacestation.mappers.IssPassesMapper;
import reactor.core.publisher.Mono;

@Service
public class IssApiServiceImpl implements IssApiService {
    private final String apiPass;
    private final String apiAstros;
    private final IssPassesMapper issPassesMapper = Mappers.getMapper( IssPassesMapper.class );


    public IssApiServiceImpl(@Value("${api.pass}") String apiPass,
                             @Value( "${api.astros}" ) String apiAstros) {
        this.apiPass = apiPass;
        this.apiAstros = apiAstros;
    }

    @Override
    public Mono<IssPassesRequest> createIssPassesRequest(MultiValueMap<String, String> params){
        return WebClient
                .create(apiPass)
                .get()
                .uri( uri->uri.queryParams( params ).build(  ) )
                .accept( MediaType.APPLICATION_JSON )
                .exchangeToMono( response->response.bodyToMono( StationPassesRequestDTO.class ) )
                .map( dto-> issPassesMapper.toIssPassesRequest( dto.getRequestDTO(), dto.getResponseDTO() ) );
    }

    @Override
    public AstronautsDTO getAstronauts() {
        return WebClient
                .create(apiAstros)
                .get()
                .uri( UriBuilder::build )
                .accept( MediaType.APPLICATION_JSON )
                .exchangeToMono( response-> response.bodyToMono( AstronautsDTO.class ))
                .block();
    }
}
