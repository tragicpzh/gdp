package org.t01.gdp.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class testController {
    @GetMapping("/page/{path}")
    public ModelAndView toPage(@PathVariable("path") String path){
        System.out.println( "/student/listSubject/"+path);
        ModelAndView mv=new ModelAndView("/student/listSubject/"+path);
        return mv;
    }
}
