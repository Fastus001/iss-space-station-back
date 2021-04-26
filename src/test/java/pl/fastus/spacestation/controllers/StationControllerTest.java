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
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.dto.*;
import pl.fastus.spacestation.services.StationLocationService;
import pl.fastus.spacestation.services.StationPassesRequestService;
import pl.fastus.spacestation.services.StationWebClientService;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class StationControllerTest {

    @Mock
    StationPassesRequestService stationPassesRequestService;

    @Mock
    StationLocationService stationLocationService;

    @Mock
    StationWebClientService stationWebClientService;

    @InjectMocks
    StationController stationController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        stationController = new StationController(stationPassesRequestService, stationLocationService, stationWebClientService);

        mockMvc = MockMvcBuilders.standaloneSetup(stationController).build();
    }

    @Test
    void getPassesRequest() throws Exception {
        //given
        PassesRequestDTO body = new PassesRequestDTO();
        body.setLongitude(BigDecimal.valueOf(179.999));
        body.setLatitude(BigDecimal.valueOf(-79.9999));

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setLongitude(1D);

        StationPassesRequestDTO savedRequest = new StationPassesRequestDTO();
        savedRequest.setMessage("success");
        savedRequest.setRequestDTO(requestDTO);

        given(stationWebClientService.createPassesRequest(any())).willReturn(Mono.just(savedRequest));
        given(stationPassesRequestService.saveStationPassRequest(any(StationPassesRequestDTO.class))).willReturn(savedRequest);
        //when
        mockMvc.perform(post("/api/iss/passTimes")
                .content(asJsonString(body))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message",is("success")))
                .andExpect(jsonPath("$.request.longitude", is(1.0)));

        //then
        verify(stationPassesRequestService, times(1)).saveStationPassRequest(any());
        verify(stationWebClientService, times(1)).createPassesRequest(any());
    }

    @Test
    void passesRequestShouldFailBecauseOfValidationConstrains() throws Exception {
        //given
        PassesRequestDTO body = new PassesRequestDTO();
        body.setLongitude(BigDecimal.ONE);
        body.setLatitude(BigDecimal.valueOf(80.001));

        mockMvc.perform(post("/api/iss/passTimes")
                .content(asJsonString(body))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());


        verify(stationPassesRequestService, times(0)).saveStationPassRequest(any());
    }

    @Test
    void getPassesRequestById() throws Exception {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setLongitude(1D);

        StationPassesRequestDTO savedRequest = new StationPassesRequestDTO();
        savedRequest.setMessage("success");
        savedRequest.setRequestDTO(requestDTO);

        given(stationPassesRequestService.findStationPassRequestById(any())).willReturn(savedRequest);

        mockMvc.perform(get("/api/iss/passTimes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message",is("success")))
                .andExpect(jsonPath("$.request.longitude", is(1.0)));
    }

    @Test
    void saveStationLocation() throws Exception {
        StationNowDTO stationNowDTO = new StationNowDTO();
        stationNowDTO.setMessage("success");
        stationNowDTO.setTimestamp(123456L);
        stationNowDTO.setIssPositionDTO(new IssPositionDTO(25.25,50.20));

        StationNow stationNow = new StationNow();
        stationNow.setId(1L);
        stationNow.setLatitude(25.25);
        stationNow.setLongitude(50.20);
        stationNow.setTimeStamp(123456L);

        given(stationLocationService.save(any(StationNowDTO.class))).willReturn(stationNow);

        mockMvc.perform(post("/api/iss/location")
                    .content(asJsonString(stationNowDTO))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.latitude", is(25.25)))
                    .andExpect(jsonPath("$.longitude", is(50.20)))
                    .andExpect(jsonPath("$.timeStamp", is(123456)));

        verify(stationLocationService, times(1)).save(any());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
