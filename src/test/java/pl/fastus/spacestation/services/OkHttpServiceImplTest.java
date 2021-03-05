package pl.fastus.spacestation.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import pl.fastus.spacestation.converters.RequestToIssPassesRequestConverter;
import pl.fastus.spacestation.converters.ResponseToIssPassesConverter;
import pl.fastus.spacestation.domain.IssNow;
import pl.fastus.spacestation.domain.IssPassesRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class OkHttpServiceImplTest {
    private static final int NUMBER_OF_PASSES = 10;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    OkHttpServiceImpl service;

    private RequestToIssPassesRequestConverter requestConverter;
    private ResponseToIssPassesConverter responseConverter;


    @BeforeEach
    void setService(){
        service = new OkHttpServiceImpl(restTemplate, requestConverter, responseConverter);
    }

    @Test
    void getIssNow() {
        final IssNow issNow = service.getIssNow();

        assertNotNull( issNow );
    }

}