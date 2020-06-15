package org.t01.gdp.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.domain.TimeAxis;
import org.t01.gdp.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

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
    @PostMapping("/crowssreview")
    public boolean cross_review_create(List<Student> list){
        studentService.cross_review_create(list);
        return true;
    }
}
