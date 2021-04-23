package pl.fastus.spacestation.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.fastus.spacestation.domain.Astronaut;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.dto.AstronautsDTO;
import pl.fastus.spacestation.domain.dto.PassesRequestDTO;
import pl.fastus.spacestation.domain.dto.StationNowDTO;
import pl.fastus.spacestation.domain.dto.StationPassesRequestDTO;
import pl.fastus.spacestation.services.AstronautServiceImpl;
import pl.fastus.spacestation.services.IssPassesRequestService;
import pl.fastus.spacestation.services.StationNowService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/iss")
@RestController
public class IssController {

    private final IssPassesRequestService issPassesRequestService;
    private final StationNowService stationNowService;
    private final AstronautServiceImpl astronautService;


    @PostMapping("/passTimes")
    @ResponseStatus(HttpStatus.CREATED)
    public StationPassesRequestDTO getPassesRequest(@Valid @RequestBody PassesRequestDTO body){
        log.info("allParams = " + body);
        return issPassesRequestService.saveDTO(body);
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

    @PostMapping("/astros")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Astronaut> saveAstronautsInSpace(@RequestBody AstronautsDTO astronautsDTO){
        return astronautService.saveAstros(astronautsDTO);
    }
}
