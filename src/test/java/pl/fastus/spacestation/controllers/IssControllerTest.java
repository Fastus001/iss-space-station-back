package pl.fastus.spacestation.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.WebDataBinder;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.dto.AstronautsDTO;
import pl.fastus.spacestation.services.IssApiService;
import pl.fastus.spacestation.services.IssPassesRequestService;
import pl.fastus.spacestation.services.StationNowService;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IssControllerTest {

    @Mock
    IssApiService issApiService;

    @Mock
    StationNowService stationNowService;

    @Mock
    IssPassesRequestService issPassesRequestService;

    @Mock
    WebDataBinder webDataBinder;

    @InjectMocks
    IssController issController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        issController = new IssController( issApiService, stationNowService, issPassesRequestService );

        mockMvc = MockMvcBuilders.standaloneSetup( issController ).build();
    }

    @Test
    void show() throws Exception {

        mockMvc.perform( get("/iss/show") )
                .andExpect( status().isOk() )
                .andExpect( view().name( "iss/show" ) );

    }

    @Test
    public void showPassTimes() throws Exception {
        IssPassesRequest request = new IssPassesRequest();
        IssPassesRequest savedRequest = new IssPassesRequest();

        when( issApiService.createIssPassesRequest( any() ) ).thenReturn( Mono.just(request) );
        when( issPassesRequestService.save( request ) ).thenReturn( savedRequest );

        mockMvc.perform(post("/passTimes")
                .contentType( MediaType.APPLICATION_FORM_URLENCODED )
                .param( "latitude", "20.0" )
                .param( "longitude", "-25.0" )
                .param( "altitude", "100" )
                .param( "numberOfPasses","3" ))
                .andExpect(status().isOk())
                .andExpect(view().name("iss/showPassTimes"))
                .andExpect(model().attributeExists("issPassRequest"));

        verify( issApiService,times( 1 ) ).createIssPassesRequest( any() );
        verify( issPassesRequestService,times( 1 ) ).save( any() );
    }


    @Test
    void showAstronautsInSpace() throws Exception {
        AstronautsDTO astronautsToReturn = new AstronautsDTO();
        when( issApiService.getAstronauts() ).thenReturn( astronautsToReturn );

        mockMvc.perform( get( "/iss/astronauts" ) )
                .andExpect( status().isOk() )
                .andExpect( view().name( "iss/showAstronauts" ) )
                .andExpect( model().attributeExists( "astronauts" ) );

        verify( issApiService,times( 1 ) ).getAstronauts();
    }
}