package org.t01.gdp.controller.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {
    @GetMapping("/page/{path}")
    public ModelAndView toPage(@PathVariable("path") String path){
        return new ModelAndView("/student/listSubject/"+path);
    }
}
