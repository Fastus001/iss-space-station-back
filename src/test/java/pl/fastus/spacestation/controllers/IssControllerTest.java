package pl.fastus.spacestation.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.fastus.spacestation.commands.PassTimesCommand;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.services.IssApiService;
import pl.fastus.spacestation.services.IssPassesRequestService;
import pl.fastus.spacestation.services.StationNowService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IssControllerTest {

    @Mock
    IssApiService issApiService;

    @Mock
    StationNowService stationNowService;

    @Mock
    IssPassesRequestService issPassesRequestService;

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
        final StationNow returnedStationNow = StationNow.builder().id( 1L ).build();
        when( issApiService.getIssNow()).thenReturn( returnedStationNow);
        when( stationNowService.save( any() ) ).thenReturn( returnedStationNow);

        mockMvc.perform( get("/iss/show") )
                .andExpect( status().isOk() )
                .andExpect( view().name( "iss/show" ) )
                .andExpect( model().attributeExists( "stationNow" ) );

        verify( issApiService, times( 1 ) ).getIssNow();
        verify( stationNowService, times( 1 ) ).save( any() );
    }

    @Test
    public void testGetShowPassTimesForm() throws Exception {
        PassTimesCommand command = new PassTimesCommand();

        mockMvc.perform(get("/iss/passTimes"))
                .andExpect(status().isOk())
                .andExpect(view().name("iss/passtimesform"))
                .andExpect(model().attributeExists("passTimes"));
    }




}