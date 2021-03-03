package pl.fastus.spacestation.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import pl.fastus.spacestation.domain.IssNow;
import pl.fastus.spacestation.domain.IssPosition;

import java.io.IOException;
import java.util.Objects;

@Service
public class OkHttpServiceImpl implements OkHttpService{
    private static final String ISS_NOW_URL = "http://api.open-notify.org/iss-now.json";

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
        Gson gson = new Gson();
        var json = gson.fromJson( Objects.requireNonNull( response.body() ).string(), JsonObject.class );

        IssPosition issPosition = gson.fromJson( json.get( "iss_position" ), IssPosition.class );

        return IssNow.builder()
                .timeStamp( json.get( "timestamp" )
                                    .getAsLong() )
                .issPosition( issPosition )
                .build();
    }
}
