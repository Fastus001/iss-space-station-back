package pl.fastus.spacestation.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.fastus.spacestation.domain.IssNow;
import pl.fastus.spacestation.services.IssNowService;
import pl.fastus.spacestation.services.OkHttpService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IssControllerTest {

    @Mock
    OkHttpService okHttpService;

    @Mock
    IssNowService issNowService;

    @InjectMocks
    IssController issController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        issController = new IssController( okHttpService,issNowService );

        mockMvc = MockMvcBuilders.standaloneSetup( issController ).build();
    }

    @Test
    void show() throws Exception {
        final IssNow returnedIssNow = IssNow.builder().id( 1L ).build();
        when(okHttpService.getIssNow()).thenReturn( returnedIssNow );
        when( issNowService.save( any() ) ).thenReturn( returnedIssNow );

        mockMvc.perform( get("/iss/show") )
                .andExpect( status().isOk() )
                .andExpect( view().name( "iss/show" ) )
                .andExpect( model().attributeExists( "issNow" ) );

        verify( okHttpService,times( 1 ) ).getIssNow();
        verify( issNowService,times( 1 ) ).save( any() );


    }
}