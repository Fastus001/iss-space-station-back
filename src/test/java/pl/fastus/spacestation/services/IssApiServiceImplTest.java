package pl.fastus.spacestation.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.dto.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class IssApiServiceImplTest {

    public static MockWebServer mockWebServer;

    IssApiApiServiceImpl service;

    ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void setService(){
        String baseUrl =  String.format( "http://localhost:%s", mockWebServer.getPort() );
        service = new IssApiApiServiceImpl( baseUrl, baseUrl );
        objectMapper = new ObjectMapper();
    }

    @Test
    void getIssNowTest() throws JsonProcessingException {
        IssPosition position= new IssPosition(25.25,20.20);
        StationNowDTO mockStationNowDTO = new StationNowDTO(position, "message",123456L);

        mockWebServer.enqueue( new MockResponse().setBody( objectMapper.writeValueAsString( mockStationNowDTO ) )
                                        .addHeader( "Content-Type", "application/json" ));

        final StationNow issNow = service.getIssNow();

        System.out.println( "issNow = " + issNow );
        assertNotNull(issNow);
        assertEquals( 25.25, issNow.getPosition().getLatitude() );
        assertEquals( 123456, issNow.getTimeStamp() );
    }

    @Test
    void createIssPassesRequestTest() throws JsonProcessingException, InterruptedException {
        RequestDTO requestDTO = new RequestDTO(25.20,25.20,15,2,123456L);

        ResponseDTO response1 = new ResponseDTO(456789L,456 );
        ResponseDTO response2 = new ResponseDTO(1234667L,999 );

        StationPassesRequestDTO passesRequestDTO = new StationPassesRequestDTO();
        passesRequestDTO.setMessage( "message" );
        passesRequestDTO.setRequestDTO( requestDTO );
        passesRequestDTO.setResponseDTO( List.of(response1, response2) );

        mockWebServer.enqueue( new MockResponse().setBody( objectMapper.writeValueAsString( passesRequestDTO ) )
                                       .addHeader( "Content-Type", "application/json" ));

        final IssPassesRequest block = service.createIssPassesRequest( getValueMap() ).block();


        assertNotNull(block);
        assertEquals( 2, block.getResponses().size() );
        assertEquals( 25.20, block.getLatitude());
        assertEquals( 15, block.getAltitude());
    }

    @NotNull
    private MultiValueMap<String, String> getValueMap() {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add( "lat", "25.20" );
        multiValueMap.add( "lon", "25.20" );
        multiValueMap.add( "alt", "15" );
        multiValueMap.add( "n", "2" );
        return multiValueMap;
    }

}