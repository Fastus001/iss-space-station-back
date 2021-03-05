package pl.fastus.spacestation.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import pl.fastus.spacestation.domain.IssNow;
import pl.fastus.spacestation.domain.IssPasses;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.IssPosition;
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
        String url = "http://api.open-notify.org/iss-pass.json"+urlPart;
        Request request = new Request.Builder().url( url).build();

        try (Response response = client.newCall( request ).execute()) {
            return createIssPassesRequest(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private IssPassesRequest createIssPassesRequest(Response response) throws IOException {
        var json = gson.fromJson( Objects.requireNonNull( response.body() ).string(), JsonObject.class );

        final IssPassesRequest issPassesRequest = buildIssPassesRequest( json );

        return addPasses( json, issPassesRequest );
    }

    private IssPassesRequest buildIssPassesRequest(JsonObject json) {
        final RequestJSON request = gson.fromJson( json.get( "request" ), RequestJSON.class );

        return IssPassesRequest.builder()
                .latitude( request.getLatitude() )
                .longitude( request.getLongitude() )
                .altitude( request.getAltitude() )
                .datetime( request.getDatetime() )
                .passes( request.getPasses() )
                .build();
    }

    private IssPassesRequest addPasses(JsonObject json, IssPassesRequest issPassesRequest) {
        Collection<ResponseJSON> responseJSONS = gson.fromJson( json.get( "response" ),
                                                                new TypeToken<ArrayList<ResponseJSON>>() {}.getType() );
                responseJSONS.stream()
                .map( r -> IssPasses.builder()
                        .riseTime( r.getRisetime() )
                        .duration( r.getDuration() )
                        .build() )
                .forEach( issPassesRequest::addIssPasses );
        System.out.println( "issPassesRequest = " + issPassesRequest );
        return issPassesRequest;
    }
}
