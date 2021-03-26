package pl.fastus.spacestation.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.fastus.spacestation.commands.PassTimesCommand;
import pl.fastus.spacestation.domain.IssPassesRequest;
import pl.fastus.spacestation.domain.StationNow;
import pl.fastus.spacestation.domain.dto.AstronautsDTO;
import pl.fastus.spacestation.domain.dto.StationNowDTO;
import pl.fastus.spacestation.mappers.StationMapper;
import pl.fastus.spacestation.services.IssApiService;
import pl.fastus.spacestation.services.IssPassesRequestService;
import pl.fastus.spacestation.services.StationNowService;

@Slf4j
@Controller
public class IssController {
    private static final String ISS_PASS_TIMES_FORM = "iss/passtimesform";

    private final IssApiService issApiService;
    private final StationNowService stationNowService;
    private final IssPassesRequestService issPassesRequestService;

    private WebDataBinder webDataBinder;

    public IssController(IssApiService issApiService, StationNowService stationNowService,
                         IssPassesRequestService issPassesRequestService) {
        this.issApiService = issApiService;
        this.stationNowService = stationNowService;
        this.issPassesRequestService = issPassesRequestService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        this.webDataBinder = webDataBinder;
    }

    @RequestMapping({"iss/show", "iss/showlocation"})
    public String show(){
        return "iss/show";
    }

    @RequestMapping("/iss/passTimes")
    public String passTimes(Model model){
        model.addAttribute( "passTimes", new PassTimesCommand() );
        return ISS_PASS_TIMES_FORM;
    }

    @PostMapping("passTimes")
    public String showPassTimes(@ModelAttribute("passTimes") PassTimesCommand command, Model model){

        webDataBinder.validate();
        final BindingResult bindingResult = webDataBinder.getBindingResult();

        if( bindingResult.hasErrors() ){
            bindingResult.getAllErrors().forEach(objectError -> log.debug( objectError.toString()) );

            return ISS_PASS_TIMES_FORM;
        }

        final IssPassesRequest savedIssPassesRequest = issPassesRequestService
                .save( issApiService.createIssPassesRequest( command.getUriParams() ).block());

        model.addAttribute( "issPassRequest",savedIssPassesRequest);

        return "iss/showPassTimes";
    }

    @RequestMapping("/iss/astronauts")
    public String showAstronautsInSpace(){
        return "iss/showAstronauts";
    }

    @PostMapping(path = "/iss/now", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody StationNow createStationNow(@RequestBody StationNowDTO stationNowDTO){
        return stationNowService.save( StationMapper.INSTANCE.stationNowDTOtoStationNow(stationNowDTO ));
    }

    @PostMapping(path = "/iss/peoples", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void createPeoplesInSpace(@RequestBody AstronautsDTO astronautsDTO){
        System.out.println( "astronautsDTO = " + astronautsDTO );
        //Todo - save astronauts
    }
}
