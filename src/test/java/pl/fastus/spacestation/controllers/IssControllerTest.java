package pl.fastus.spacestation.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.fastus.spacestation.services.IssPassesRequestService;
import pl.fastus.spacestation.services.StationNowService;

@ExtendWith(MockitoExtension.class)
class IssControllerTest {

    @Mock
    IssPassesRequestService issPassesRequestService;

    @Mock
    StationNowService stationNowService;


    @InjectMocks
    IssController issController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        issController = new IssController(  issPassesRequestService, stationNowService );

        mockMvc = MockMvcBuilders.standaloneSetup( issController ).build();
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
