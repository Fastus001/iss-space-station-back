package pl.fastus.spacestation.services;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.fastus.spacestation.converters.StationMapper;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.apinow.ApiNow;
import pl.fastus.spacestation.domain.apipass.ApiPass;

@Service
public class IssServiceImpl implements IssService {
    private final String apiNow;
    private final String apiPass;
    private final StationMapper mapper = Mappers.getMapper( StationMapper.class );

    private final RestTemplate restTemplate;


    public IssServiceImpl(RestTemplate restTemplate, @Value("${api.now}") String apiNow,
                           @Value("${api.pass}") String apiPass) {
        this.restTemplate = restTemplate;
        this.apiNow = apiNow;
        this.apiPass = apiPass;
    }

    @Override
    public StationNow getIssNow() {
        return mapper.convertFromApiNow( restTemplate.getForObject( apiNow, ApiNow.class ) );
    }

    @Override
    public IssPassesRequest createIssPassesRequest(MultiValueMap<String, String> params){
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString( apiPass )
                .queryParams(params);

        final ApiPass template = restTemplate.getForObject( uriBuilder.toUriString(), ApiPass.class);

        return mapper.requestToPassesRequest( template.getRequest(), template.getResponse());
    }

}
