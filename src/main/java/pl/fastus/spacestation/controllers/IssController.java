package pl.fastus.spacestation.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.fastus.spacestation.commands.PassTimesCommand;
import pl.fastus.spacestation.domain.IssNow;
import pl.fastus.spacestation.services.IssNowService;
import pl.fastus.spacestation.services.OkHttpService;

@Slf4j
@Controller
public class IssController {
    private static final String ISS_PASS_TIMES_FORM = "iss/passtimesform";

    private final OkHttpService okHttpService;
    private final IssNowService issNowService;

    private WebDataBinder webDataBinder;

    public IssController(OkHttpService okHttpService, IssNowService issNowService) {
        this.okHttpService = okHttpService;
        this.issNowService = issNowService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        this.webDataBinder = webDataBinder;
    }

    @RequestMapping({"iss/show", "iss/showlocation"})
    public String show(Model model){
        final IssNow issNow = okHttpService.getIssNow();
        model.addAttribute( "issNow", issNowService.save( issNow ));
        return "iss/show";
    }

    @RequestMapping("/iss/passTimes")
    public String passTimes(Model model){
        model.addAttribute( "passTimes", new PassTimesCommand() );
        return ISS_PASS_TIMES_FORM;
    }

    @PostMapping("passTimes")
    public String showPassTimes(@ModelAttribute("passTimes") PassTimesCommand command){
        webDataBinder.validate();
        final BindingResult bindingResult = webDataBinder.getBindingResult();

        if( bindingResult.hasErrors() ){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return ISS_PASS_TIMES_FORM;
        }

        //TODO - get data and show passes
        System.out.println( "command = " + command );


        return "iss/showPassTimes";
    }
}
