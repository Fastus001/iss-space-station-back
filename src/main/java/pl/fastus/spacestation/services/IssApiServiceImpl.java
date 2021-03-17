package pl.fastus.spacestation.services;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import pl.fastus.spacestation.converters.IssPassesMapper;
import pl.fastus.spacestation.converters.StationMapper;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.dto.AstronautsDTO;
import pl.fastus.spacestation.domain.dto.StationNowDTO;
import pl.fastus.spacestation.domain.dto.StationPassesRequestDTO;
import reactor.core.publisher.Mono;

@Service
public class IssApiServiceImpl implements IssApiService {
    private final String apiNow;
    private final String apiPass;
    private final String apiAstros;
    private final StationMapper stationMapper = Mappers.getMapper( StationMapper.class );
    private final IssPassesMapper issPassesMapper = Mappers.getMapper( IssPassesMapper.class );


    public IssApiServiceImpl(@Value("${api.now}") String apiNow, @Value("${api.pass}") String apiPass,
                             @Value( "${api.astros}" ) String apiAstros) {
        this.apiNow = apiNow;
        this.apiPass = apiPass;
        this.apiAstros = apiAstros;
    }

    @Override
    public StationNow getIssNow() {
         return  WebClient
                 .create( apiNow )
                .get()
                .uri( UriBuilder::build )
                .accept( MediaType.APPLICATION_JSON )
                .exchangeToMono( response -> response.bodyToMono( StationNowDTO.class ) )
                .map( stationMapper::stationNowDTOtoStationNow )
                .block();
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
