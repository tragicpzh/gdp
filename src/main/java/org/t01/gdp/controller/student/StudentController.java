package org.t01.gdp.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.common.Result;
import org.t01.gdp.domain.*;
import org.t01.gdp.service.StudentService;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/getSubject")
    @ResponseBody
    public Subject getSubject(@RequestParam(name = "id") long id) {
        return studentService.getSubjectById(id);
    }

    @PostMapping("/crossReview/create")//交叉评阅自动分配
    public boolean cross_review_create(List<Student> list){
        studentService.cross_review_create(list);
        return true;
    }


    @PostMapping("/crossReview/select")//交叉评阅信息查找
    @ResponseBody
    public Object select_cross_review(HttpServletRequest request){
        String student_id=((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        Crossreview crossreview=studentService.selectCrossReview(student_id);
        return Result.success(crossreview,"success");
    }
}
