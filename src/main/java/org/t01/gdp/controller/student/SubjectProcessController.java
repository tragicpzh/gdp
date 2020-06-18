package org.t01.gdp.controller.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.domain.TimeAxis;
import org.t01.gdp.service.SubjectProcessService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class SubjectProcessController {
    private final SubjectProcessService subjectProcessService;

    @ResponseBody
    @RequestMapping("/getTimeState")
    public String getTimeState() {
        return String.valueOf(TimeAxis.getTimeAxisState());
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
    @RequestMapping("/getSubject")
    public Subject getSubject(@RequestParam(name = "id") long id) {
        return subjectProcessService.getSubjectById(id);
    }
}
