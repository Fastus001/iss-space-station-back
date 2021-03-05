package pl.fastus.spacestation.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.fastus.spacestation.converters.RequestToIssPassesRequestConverter;
import pl.fastus.spacestation.converters.ResponseToIssPassesConverter;
import pl.fastus.spacestation.domain.*;
import pl.fastus.spacestation.json.RequestJSON;
import pl.fastus.spacestation.json.ResponseJSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Service
public class OkHttpServiceImpl implements OkHttpService{
    private static final String ISS_NOW_URL = "http://api.open-notify.org/iss-now.json";

    private final Gson gson = new Gson();

    private final OkHttpClient client = new OkHttpClient();

    private RestTemplate restTemplate;

    private RequestToIssPassesRequestConverter requestConverter;
    private ResponseToIssPassesConverter responseConverter;

    public OkHttpServiceImpl(RestTemplate restTemplate, RequestToIssPassesRequestConverter requestConverter,
                             ResponseToIssPassesConverter responseConverter) {
        this.restTemplate = restTemplate;
        this.requestConverter = requestConverter;
        this.responseConverter = responseConverter;
    }

    @Override
    public IssNow getIssNow() {
        Request request = new Request.Builder().url( ISS_NOW_URL).build();
        IssNow currentLocation = null;
            try (Response response = client.newCall( request ).execute()) {
                currentLocation =  createIssNow( response );
            } catch (IOException e) {
                e.printStackTrace();
            }
        return currentLocation;
    }

    private IssNow createIssNow(Response response) throws IOException {
        var json = gson.fromJson( Objects.requireNonNull( response.body() ).string(), JsonObject.class );

        IssPosition issPosition = gson.fromJson( json.get( "iss_position" ), IssPosition.class );

        return IssNow.builder()
                .timeStamp( json.get( "timestamp" )
                                    .getAsLong() )
                .issPosition( issPosition )
                .build();
    }

    public IssPassesRequest createIssPassesRequest(String urlPart){
        final Example template = restTemplate.getForObject("http://api.open-notify.org/iss-pass.json" + urlPart,
                Example.class);

        final IssPassesRequest request1 = requestConverter.convert(template.getRequest());
        template.getResponse().stream().map(response -> responseConverter.convert(response))
                .forEach(request1::addIssPasses);
        return request1;
    }

}
