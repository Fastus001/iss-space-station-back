package pl.fastus.spacestation.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.fastus.spacestation.domain.IssNow;
import pl.fastus.spacestation.domain.IssPassesRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OkHttpServiceImplTest {
    private static final int NUMBER_OF_PASSES = 10;
    OkHttpServiceImpl service;


    @BeforeEach
    void setService(){
        service = new OkHttpServiceImpl();
    }

    @Test
    void getIssNow() {
        final IssNow issNow = service.getIssNow();

        assertNotNull( issNow );
    }

    @Test
    void createIssPassesRequest() {

        String urlPart = "?lat=10&lon=10&n="+NUMBER_OF_PASSES;
        final IssPassesRequest result = service.createIssPassesRequest( urlPart );

        assertNotNull( result );
        assertEquals( NUMBER_OF_PASSES,result.getResponses().size() );
    }
}