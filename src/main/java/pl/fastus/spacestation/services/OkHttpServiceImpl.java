package pl.fastus.spacestation.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.fastus.spacestation.commands.IssNow;
import pl.fastus.spacestation.converters.RequestToIssPassesRequestConverter;
import pl.fastus.spacestation.converters.ResponseToIssPassesConverter;
import pl.fastus.spacestation.domain.*;

import java.io.IOException;
import java.util.Objects;

@Service
public class OkHttpServiceImpl implements OkHttpService{
    private static final String ISS_NOW_URL = "http://api.open-notify.org/iss-now.json";

    private final Gson gson = new Gson();

    private final OkHttpClient client = new OkHttpClient();

    private final RestTemplate restTemplate;

    private final RequestToIssPassesRequestConverter requestConverter;
    private final ResponseToIssPassesConverter responseConverter;

    public OkHttpServiceImpl(RestTemplate restTemplate, RequestToIssPassesRequestConverter requestConverter,
                             ResponseToIssPassesConverter responseConverter) {
        this.restTemplate = restTemplate;
        this.requestConverter = requestConverter;
        this.responseConverter = responseConverter;
    }

    @Override
    public StationNow getIssNow() {
        final IssNow forObject = restTemplate.getForObject(ISS_NOW_URL, IssNow.class);
        System.out.println("forObject = " + forObject);

        Request request = new Request.Builder().url( ISS_NOW_URL).build();
        StationNow currentLocation = null;
            try (Response response = client.newCall( request ).execute()) {
                currentLocation =  createIssNow( response );
            } catch (IOException e) {
                e.printStackTrace();
            }
        return currentLocation;
    }

    private StationNow createIssNow(Response response) throws IOException {
        var json = gson.fromJson( Objects.requireNonNull( response.body() ).string(), JsonObject.class );

        Position position = gson.fromJson( json.get( "iss_position" ), Position.class );

        return StationNow.builder()
                .timeStamp( json.get( "timestamp" )
                                    .getAsLong() )
                .position(position)
                .build();
    }

    public IssPassesRequest createIssPassesRequest(String urlPart){
        final Example template = restTemplate.getForObject("http://api.open-notify.org/iss-pass.json" + urlPart,
                Example.class);

        final IssPassesRequest request1 = requestConverter.convert(template.getRequest());
        template.getResponse().stream().map(responseConverter::convert)
                .forEach(request1::addIssPasses);


        return request1;
    }

}
