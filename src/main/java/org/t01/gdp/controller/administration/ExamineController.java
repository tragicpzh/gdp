package org.t01.gdp.controller.administration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.domain.SubjectExample;
import org.t01.gdp.service.SubjectService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ExamineController {
    @Autowired
    SubjectService subjectService;
    //审核通过 直接传subject
    @PutMapping("/examine/pass")
    public Object examine_pass(@RequestBody Subject subject){
        //Map<String,Object> map=new HashMap<>();
        subjectService.updateById(subject,"审核通过");
        //map.put("success",true);
        return "/administrator/list_Administration";
        //return map;
    }

    //审核失败 直接传subject
    @PutMapping("/examine/fail")
    public Object examine_fail(@RequestBody Subject subject){
       // Map<String,Object> map=new HashMap<>();
        subjectService.updateById(subject,"审核失败");
       // map.put("success",true);
        return "/administrator/list_Administration";
        //return map;
    }

}
