package pl.fastus.spacestation.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import pl.fastus.spacestation.converters.ResponseToIssPassesConverter;

@ExtendWith(MockitoExtension.class)
class IssServiceImplTest {
    private static final int NUMBER_OF_PASSES = 10;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    IssServiceImpl service;

    private ResponseToIssPassesConverter responseConverter;


    @BeforeEach
    void setService(){
//        service = new OkHttpServiceImpl(restTemplate, requestConverter, responseConverter);
    }

    @Test
    void getIssNow() {
//        final IssNow issNow = service.getIssNow();

//        assertNotNull( issNow );
    }

}