package pl.fastus.spacestation.bootstrap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.fastus.spacestation.domain.IssNow;
import pl.fastus.spacestation.domain.IssPosition;
import pl.fastus.spacestation.services.IssNowService;

import java.io.IOException;
import java.util.Objects;

@Component
public class DataLoader implements CommandLineRunner {
    private static final String ISS_NOW_URL = "http://api.open-notify.org/iss-now.json";

    private final IssNowService issNowService;

    public DataLoader(IssNowService issNowService) {
        this.issNowService = issNowService;
    }

    @Override
    public void run(String... args) throws Exception {

        int size = issNowService.findAll().size();
        if ( size == 0 ) {
//            loadData();
        }

    }

    private void loadData() throws InterruptedException {

        Request request = new Request.Builder().url(ISS_NOW_URL).build();
        OkHttpClient client = new OkHttpClient();
        int counter = 10;
        while (counter-->0) {
            try (Response response = client.newCall( request ).execute()) {
                issNowService.save( createIssNow( response ) );
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.sleep( 1000 );
        }
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
