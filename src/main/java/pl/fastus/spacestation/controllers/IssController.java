package pl.fastus.spacestation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IssController {

    @RequestMapping({"iss/show", "iss/showlocation"})
    public String show(){
        return "iss/show";
    }
}
