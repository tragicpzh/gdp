package org.t01.gdp.controller.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.domain.TimeAxis;
import org.t01.gdp.service.StudentService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @ResponseBody
    @RequestMapping("/getTimeState")
    public String getTimeState() {
        return String.valueOf(TimeAxis.getTimeAxisState());
    }

    @ResponseBody
    @RequestMapping("/getStudentInfo")
    public Student getStudentInfo(@RequestParam(name = "id") String id) {
        return studentService.getStudentInfoById(id);
    }

    @ResponseBody
    @RequestMapping("/upgradeState")
    public String upgradeState(Student student) {
        return studentService.updateState(student);
    }

    @ResponseBody
    @RequestMapping("/getSubject")
    public Subject getSubject(@RequestParam(name = "id") long id) {
        return studentService.getSubjectById(id);
    }
}
