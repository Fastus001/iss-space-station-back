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
import pl.fastus.spacestation.domain.dto.AstronautsDTO;
import pl.fastus.spacestation.domain.dto.PeopleDTO;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class IssApiServiceImplTest {

    public static MockWebServer mockWebServer;

    IssApiServiceImpl service;

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
        service = new IssApiServiceImpl(baseUrl, baseUrl );
        objectMapper = new ObjectMapper();
    }

    @Test
//    void createIssPassesRequestTest() throws JsonProcessingException, InterruptedException {
//        RequestDTO requestDTO = new RequestDTO(25.20,25.20,15,2,123456L);
//
//        ResponseDTO response1 = new ResponseDTO(456789L,456 );
//        ResponseDTO response2 = new ResponseDTO(1234667L,999 );
//
//        StationPassesRequestDTO passesRequestDTO = new StationPassesRequestDTO();
//        passesRequestDTO.setMessage( "message" );
//        passesRequestDTO.setRequestDTO( requestDTO );
//        passesRequestDTO.setResponseDTO( List.of(response1, response2) );
//
//        mockWebServer.enqueue( new MockResponse().setBody( objectMapper.writeValueAsString( passesRequestDTO ) )
//                                       .addHeader( "Content-Type", "application/json" ));
//
//        final IssPassesRequest block = service.createPassesRequest( getValueMap() ).block();
//
//
//        assertNotNull(block);
//        assertEquals( 2, block.getResponses().size() );
//        assertEquals( 25.20, block.getLatitude());
//        assertEquals( 15, block.getAltitude());
//    }

    @NotNull
    private MultiValueMap<String, String> getValueMap() {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add( "lat", "25.20" );
        multiValueMap.add( "lon", "25.20" );
        multiValueMap.add( "alt", "15" );
        multiValueMap.add( "n", "2" );
        return multiValueMap;
    }


    @Test
    void getAstronauts() throws JsonProcessingException {
        PeopleDTO people1 = new PeopleDTO();
        people1.setName( "Name" );
        people1.setCraft( "Craft" );

        PeopleDTO people2 = new PeopleDTO();
        people2.setName( "Name" );
        people2.setCraft( "Craft" );

        AstronautsDTO astronautsDTO = new AstronautsDTO();
        astronautsDTO.setNumber( 2 );
        astronautsDTO.setMessage( "message" );
        astronautsDTO.setPeople( List.of(people1, people2) );

        mockWebServer.enqueue( new MockResponse().setBody( objectMapper.writeValueAsString( astronautsDTO ) )
                                       .addHeader( "Content-Type", "application/json" ));

        final AstronautsDTO astronauts = service.getAstronauts();

        assertNotNull(astronauts);
        assertEquals( 2, astronauts.getNumber() );
        assertEquals( 2, astronauts.getPeople().size());
    }
}
