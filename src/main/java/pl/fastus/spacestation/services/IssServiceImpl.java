package pl.fastus.spacestation.services;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import pl.fastus.spacestation.converters.StationMapper;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.dto.StationNowDTO;
import pl.fastus.spacestation.domain.dto.StationPassesRequestDTO;

@Service
public class IssServiceImpl implements IssService {
    private final String apiNow;
    private final String apiPass;
    private final StationMapper mapper = Mappers.getMapper( StationMapper.class );


    public IssServiceImpl(@Value("${api.now}") String apiNow,@Value("${api.pass}") String apiPass) {
        this.apiNow = apiNow;
        this.apiPass = apiPass;
    }

    @Override
    public StationNow getIssNow() {
         return  WebClient.create( apiNow )
                .get()
                .uri( UriBuilder::build )
                .exchangeToMono( response -> response.bodyToMono( StationNowDTO.class ) )
                .map( mapper::toStationNow )
                .block();
    }

    @Override
    public IssPassesRequest createIssPassesRequest(MultiValueMap<String, String> params){
        return WebClient.create(apiPass)
                .get()
                .uri( uri->uri.queryParams( params ).build(  ) )
                .exchangeToMono( response->response.bodyToMono( StationPassesRequestDTO.class ) )
                .map( dto->mapper.toIssPassesRequest( dto.getRequest(), dto.getResponse() ) )
                .block();
    }
}
