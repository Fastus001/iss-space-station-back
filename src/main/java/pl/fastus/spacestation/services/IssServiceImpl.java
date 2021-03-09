package pl.fastus.spacestation.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.mapstruct.factory.Mappers;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.fastus.spacestation.converters.RequestMapper;
import pl.fastus.spacestation.converters.ResponseToIssPassesConverter;
import pl.fastus.spacestation.domain.Example;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.Position;
import pl.fastus.spacestation.domain.StationNow;

@Service
public class IssServiceImpl implements IssService {
    private static final String ISS_NOW_URL = "http://api.open-notify.org/iss-now.json";

    private final Gson gson = new Gson();
    private final RestTemplate restTemplate;

    private final ResponseToIssPassesConverter responseConverter;

    public IssServiceImpl(RestTemplate restTemplate, ResponseToIssPassesConverter responseConverter) {
        this.restTemplate = restTemplate;
        this.responseConverter = responseConverter;
    }

    @Override
    public StationNow getIssNow() {
        final ResponseEntity<Object> exchange = restTemplate
                .exchange(ISS_NOW_URL, HttpMethod.GET, null, new ParameterizedTypeReference<Object>() {});
        return createIssNow( exchange.getBody().toString() );
    }

    private StationNow createIssNow(String text) {

        var json = gson.fromJson( text, JsonObject.class );

        Position position = gson.fromJson( json.get( "iss_position" ), Position.class );

        return StationNow.builder()
                .timeStamp( json.get( "timestamp" ).getAsLong() )
                .position(position)
                .build();
    }

    public IssPassesRequest createIssPassesRequest(String urlPart){
        final Example template = restTemplate.getForObject("http://api.open-notify.org/iss-pass.json" + urlPart,
                Example.class);


        final IssPassesRequest request1 = Mappers.getMapper( RequestMapper.class )
                .requestToPassesRequest( template.getRequest());

        template.getResponse().stream().map(responseConverter::convert)
                .forEach(request1::addIssPasses);

        return request1;
    }

}
