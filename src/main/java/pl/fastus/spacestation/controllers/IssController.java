package pl.fastus.spacestation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.fastus.spacestation.commands.PassTimesCommand;
import pl.fastus.spacestation.domain.IssNow;
import pl.fastus.spacestation.services.IssNowService;
import pl.fastus.spacestation.services.OkHttpService;

@Controller
public class IssController {

    private final OkHttpService okHttpService;
    private final IssNowService issNowService;

    public IssController(OkHttpService okHttpService, IssNowService issNowService) {
        this.okHttpService = okHttpService;
        this.issNowService = issNowService;
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
        return "iss/passtimesform";
    }
}
