package org.t01.gdp.controller.administration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrator")
public class HomeController {

    @GetMapping("/home")
    public String getHome(){
        return "administrator/home";
    }
}
