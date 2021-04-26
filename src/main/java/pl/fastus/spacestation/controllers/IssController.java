package pl.fastus.spacestation.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.dto.PassesRequestDTO;
import pl.fastus.spacestation.domain.dto.StationNowDTO;
import pl.fastus.spacestation.domain.dto.StationPassesRequestDTO;
import pl.fastus.spacestation.mappers.PassesRequestParamMapper;
import pl.fastus.spacestation.services.IssApiService;
import pl.fastus.spacestation.services.IssPassesRequestService;
import pl.fastus.spacestation.services.StationNowService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(allowedHeaders = "*",origins = "*", methods = {RequestMethod.POST})
@RequiredArgsConstructor
@RequestMapping("/api/iss")
@RestController
public class IssController {

    private final IssPassesRequestService issPassesRequestService;
    private final StationNowService stationNowService;
    private final IssApiService issApiService;


    @PostMapping("/passTimes")
    @ResponseStatus(HttpStatus.CREATED)
    public StationPassesRequestDTO getPassesRequest(@Valid @RequestBody PassesRequestDTO body){
        StationPassesRequestDTO passesRequestDTO = issApiService.createPassesRequest(PassesRequestParamMapper.getUriParams(body)).block();
        log.info("allParams = " + body);
        return issPassesRequestService.saveDTO(passesRequestDTO);
    }

    @GetMapping("/passTimes/{id}")
    public StationPassesRequestDTO getPassesRequestById(@PathVariable Long id){
        return issPassesRequestService.findById(id);
    }

    @PostMapping("/location")
    @ResponseStatus(HttpStatus.CREATED)
    public StationNow saveStationLocation(@RequestBody StationNowDTO stationNowDTO){
        log.info("New location in controller to save " + stationNowDTO);
        return stationNowService.save(stationNowDTO );
    }

}
