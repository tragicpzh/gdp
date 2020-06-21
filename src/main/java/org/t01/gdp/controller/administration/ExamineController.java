package org.t01.gdp.controller.administration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.service.SubjectService;

@RestController
public class ExamineController {
    @Autowired
    SubjectService subjectService;
    //审核通过 直接传subject
    @PutMapping("/examine/pass")
    public Object examinePass(@RequestBody Subject subject){
        subjectService.updateById(subject,"审核通过");
        return "/administrator/list_Administration";
    }

    //审核失败 直接传subject
    @PutMapping("/examine/fail")
    public Object examineFail(@RequestBody Subject subject){
        subjectService.updateById(subject,"审核失败");
        return "/administrator/list_Administration";
    }

}
