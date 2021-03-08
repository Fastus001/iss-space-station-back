package pl.fastus.spacestation.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.IssPasses;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.Position;
import pl.fastus.spacestation.services.IssNowService;
import pl.fastus.spacestation.services.IssPassesRequestService;

@Component
public class DataLoader implements CommandLineRunner {
    private static final String ISS_NOW_URL = "http://api.open-notify.org/iss-now.json";

    private final IssNowService issNowService;
    private final IssPassesRequestService issPassesRequestService;

    public DataLoader(IssNowService issNowService, IssPassesRequestService issPassesRequestService) {
        this.issNowService = issNowService;
        this.issPassesRequestService = issPassesRequestService;
    }

    @Override
    public void run(String... args) throws Exception {

        int size = issNowService.findAll().size();
        if ( size == 0 ) {
            loadData();
        }

    }

    private void loadData() throws InterruptedException {
        Position position = Position.builder()
                .latitude( 10 )
                .longitude( 25 )
                .build();

        StationNow stationNow = StationNow.builder().position( position )
                .timeStamp( 124563L ).build();

        issNowService.save(stationNow);

        IssPasses passOne = IssPasses.builder().riseTime( 12345L )
                .duration( 245 ).build();

        IssPasses passTwo = IssPasses.builder().riseTime( 12345L )
                .duration( 150 ).build();


        IssPassesRequest request = IssPassesRequest.builder()
                .latitude( 25 )
                .longitude( 25 )
                .datetime( 122122L )
                .passes( 5 )
                .build();
        request.addIssPasses( passOne );
        request.addIssPasses( passTwo );

        issPassesRequestService.save( request );

    }

}
