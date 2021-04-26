package pl.fastus.spacestation.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.fastus.spacestation.domain.dto.PassesRequestDTO;
import pl.fastus.spacestation.domain.dto.RequestDTO;
import pl.fastus.spacestation.domain.dto.StationPassesRequestDTO;
import pl.fastus.spacestation.services.AstronautServiceImpl;
import pl.fastus.spacestation.services.IssPassesRequestService;
import pl.fastus.spacestation.services.StationNowService;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class IssControllerTest {

    @Mock
    IssPassesRequestService issPassesRequestService;

    @Mock
    StationNowService stationNowService;

    @Mock
    AstronautServiceImpl astronautService;


    @InjectMocks
    IssController issController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        issController = new IssController(  issPassesRequestService, stationNowService, astronautService );

        mockMvc = MockMvcBuilders.standaloneSetup( issController ).build();
    }

    @Test
    void getPassesRequest() throws Exception {
        //given
        PassesRequestDTO body = new PassesRequestDTO();
        body.setLongitude(BigDecimal.ONE);
        body.setLatitude(BigDecimal.ONE);
        body.setNumber(BigDecimal.ONE);
        body.setAltitude(BigDecimal.ONE);

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setLongitude(1D);

        StationPassesRequestDTO savedRequest = new StationPassesRequestDTO();
        savedRequest.setMessage("success");
        savedRequest.setRequestDTO(requestDTO);

        given(issPassesRequestService.saveDTO(any(PassesRequestDTO.class))).willReturn(savedRequest);
        //when
        mockMvc.perform(post("/api/iss/passTimes")
                .content(asJsonString(body))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message",is("success")))
                .andExpect(jsonPath("$.request.longitude", is(1.0)));

        //then
        verify(issPassesRequestService, times(1)).saveDTO(any());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


//    @Test
//    void show() throws Exception {
//
//        mockMvc.perform( get("/iss/show") )
//                .andExpect( status().isOk() )
//                .andExpect( view().name( "iss/show" ) );
//    }

//    @Test
//    public void showPassTimes() throws Exception {
//        IssPassesRequest request = new IssPassesRequest();
//        IssPassesRequest savedRequest = new IssPassesRequest();
//
//        when( issApiService.createPassesRequest( any() ) ).thenReturn( Mono.just(request) );
//        when( issPassesRequestService.save( request ) ).thenReturn( savedRequest );
//
//        mockMvc.perform(post("/passTimes")
//                .contentType( MediaType.APPLICATION_FORM_URLENCODED )
//                .param( "latitude", "20.0" )
//                .param( "longitude", "-25.0" )
//                .param( "altitude", "100" )
//                .param( "numberOfPasses","3" ))
//                .andExpect(status().isOk())
//                .andExpect(view().name("iss/showPassTimes"))
//                .andExpect(model().attributeExists("issPassRequest"));
//
//        verify( issApiService,times( 1 ) ).createPassesRequest( any() );
//        verify( issPassesRequestService,times( 1 ) ).save( any() );
//    }
//
//
//    @Test
//    void showAstronautsInSpace() throws Exception {
//
//        mockMvc.perform( get( "/iss/astronauts" ) )
//                .andExpect( status().isOk() )
//                .andExpect( view().name( "iss/showAstronauts" ) );
//    }

}
