package pl.fastus.spacestation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.fastus.spacestation.services.OkHttpService;

@Controller
public class IssController {

    private final OkHttpService okHttpService;

    public IssController(OkHttpService okHttpService) {
        this.okHttpService = okHttpService;
    }

    @RequestMapping({"iss/show", "iss/showlocation"})
    public String show(Model model){
        model.addAttribute( "issNow", okHttpService.getIssNow());
        return "iss/show";
    }
}
