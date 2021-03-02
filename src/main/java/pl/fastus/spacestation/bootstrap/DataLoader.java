package pl.fastus.spacestation.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.fastus.spacestation.domain.IssNow;
import pl.fastus.spacestation.domain.IssPosition;
import pl.fastus.spacestation.services.IssNowService;
import pl.fastus.spacestation.services.IssPositionService;

@Component
public class DataLoader implements CommandLineRunner {

    private final IssNowService issNowService;
    private final IssPositionService issPositionService;

    public DataLoader(IssNowService issNowService, IssPositionService issPositionService) {
        this.issNowService = issNowService;
        this.issPositionService = issPositionService;
    }

    @Override
    public void run(String... args) throws Exception {

        int size = issNowService.findAll().size();
        if(size ==0){
            loadData();
        }

    }

    private void loadData() {
        IssPosition position = IssPosition.builder()
                .longitude( 12.25 )
                .latitude( -12.45 ).build();

        IssNow first = IssNow.builder()
                .issPosition( position )
                .timeStamp( 123456L ).build();

        issNowService.save( first );



    }
}
