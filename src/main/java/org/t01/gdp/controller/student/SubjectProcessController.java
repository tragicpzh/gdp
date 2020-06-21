package org.t01.gdp.controller.student;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.service.TimeAxisService;
import org.t01.gdp.service.SubjectProcessService;

@Controller
@RequestMapping("/student")
public class SubjectProcessController {
    @Autowired
    SubjectProcessService subjectProcessService;

    @ResponseBody
    @RequestMapping("/getTimeState")
    public String getTimeState() {
        return String.valueOf(TimeAxisService.getTimeAxisState());
    }

    @ResponseBody
    @RequestMapping("/getStudentInfo")
    public Student getStudentInfo(@RequestParam(name = "id") String id) {
        return subjectProcessService.getStudentInfoById(id);
    }

    @ResponseBody
    @RequestMapping("/upgradeState")
    public String upgradeState(@RequestParam(name = "id") String id,@RequestParam(name = "state") String state) {
        return subjectProcessService.updateState(id,state);
    }

    @ResponseBody
    @RequestMapping("/getSubjectInfo")
    public Subject getSubject(@RequestParam(name = "id") long id) {
        return subjectProcessService.getSubjectById(id);
    }
}
