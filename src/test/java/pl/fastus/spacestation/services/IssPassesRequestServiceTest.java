package pl.fastus.spacestation.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.fastus.spacestation.domain.IssPass;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.dto.RequestDTO;
import pl.fastus.spacestation.domain.dto.ResponseDTO;
import pl.fastus.spacestation.domain.dto.StationPassesRequestDTO;
import pl.fastus.spacestation.mappers.IssPassesMapper;
import pl.fastus.spacestation.repositories.IssPassesRequestRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class IssPassesRequestServiceTest {

    @Mock
    IssPassesRequestRepository repository;

    @Mock
    IssPassesMapper mapper;

    @InjectMocks
    IssPassesRequestService issPassesRequestService;

    StationPassesRequestDTO callback;
    IssPassesRequest passesRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        issPassesRequestService = new IssPassesRequestService(repository, mapper);

        callback = new StationPassesRequestDTO();
        callback.setRequestDTO(RequestDTO.builder().longitude(25D).latitude(85.25).build());
        callback.setResponseDTO(List.of(
                new ResponseDTO(1234L, 125),
                new ResponseDTO(12345L, 126)
        ));

        passesRequest = new IssPassesRequest();
        passesRequest.setId(1L);
        passesRequest.setLongitude(callback.getRequestDTO().getLongitude());
        passesRequest.setLatitude(callback.getRequestDTO().getLatitude());
        passesRequest.setResponses(Set.of(
                new IssPass(1L, 1234, 1234L),
                new IssPass(2L, 1234, 12345L)
        ));
    }

    @Test
    void saveDTO() {
        given(mapper.toIssPassesRequest(any(RequestDTO.class), anyList())).willReturn(passesRequest);
        given(repository.save(any(IssPassesRequest.class))).willReturn(passesRequest);
        given(mapper.issPassesRequestToStationPassesRequestDTO(any(IssPassesRequest.class))).willReturn(callback);

        StationPassesRequestDTO stationPassesRequestDTO = issPassesRequestService.saveDTO(callback);

        assertNotNull(stationPassesRequestDTO);
        assertEquals(2, stationPassesRequestDTO.getResponseDTO().size());
        verify(mapper, times(1)).issPassesRequestToStationPassesRequestDTO(any());
        verify(mapper, times(1)).toIssPassesRequest(any(), anyList());
        verify(repository, times(1)).save(any());
    }

    @Test
    void findById() {
        given(repository.findById(any())).willReturn(Optional.of(passesRequest));
        given(mapper.issPassesRequestToStationPassesRequestDTO(any(IssPassesRequest.class))).willReturn(callback);

        StationPassesRequestDTO byId = issPassesRequestService.findById(1L);

        assertNotNull(byId);
        assertEquals(2, byId.getResponseDTO().size());

    }
}
