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
import pl.fastus.spacestation.services.StationLocationService;
import pl.fastus.spacestation.services.StationPassesRequestService;
import pl.fastus.spacestation.services.StationWebClientService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(allowedHeaders = "*",origins = "*", methods = {RequestMethod.POST})
@RequiredArgsConstructor
@RequestMapping("/api/iss")
@RestController
public class StationController {

    private final StationPassesRequestService stationPassesRequestService;
    private final StationLocationService stationLocationService;
    private final StationWebClientService stationWebClientService;


    @PostMapping("/passTimes")
    @ResponseStatus(HttpStatus.CREATED)
    public StationPassesRequestDTO getAndSavePassesRequest(@Valid @RequestBody PassesRequestDTO body){
        StationPassesRequestDTO passesRequestDTO = stationWebClientService.createPassesRequest(PassesRequestParamMapper.getUriParams(body)).block();
        log.info("allParams = " + body);
        return stationPassesRequestService.saveStationPassRequest(passesRequestDTO);
    }

    @GetMapping("/passTimes/{id}")
    public StationPassesRequestDTO getPassesRequestById(@PathVariable Long id){
        return stationPassesRequestService.findStationPassRequestById(id);
    }

    @PostMapping("/location")
    @ResponseStatus(HttpStatus.CREATED)
    public StationNow saveStationLocation(@RequestBody StationNowDTO stationNowDTO){
        log.info("New location in controller to save " + stationNowDTO);
        return stationLocationService.save(stationNowDTO );
    }

}
