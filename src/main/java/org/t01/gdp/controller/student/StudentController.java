package org.t01.gdp.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.common.Result;
import org.t01.gdp.domain.*;
import org.springframework.web.multipart.MultipartFile;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.domain.TimeAxis;
import org.t01.gdp.domain.UserInfo;
import org.t01.gdp.service.StudentService;
import org.t01.gdp.service.UploadService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    UploadService uploadService;

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

    @ResponseBody
    @PostMapping("/crowssreview")
    public boolean cross_review_create(List<Student> list){
        studentService.cross_review_create(list);
        return true;
    }

    @ResponseBody
    @RequestMapping("/uploadOpenReport")
    public void uploadOpenReport(HttpServletRequest request, MultipartFile multipartFile){
        String studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        String path = studentId + "\\openReport\\";
        uploadService.uploadFile(multipartFile, path);
        studentService.updateStudentOpenReport(studentId, path, multipartFile.getOriginalFilename());
    }

    @ResponseBody
    @RequestMapping("/uploadMiddleReport")
    public void uploadMiddleReport(HttpServletRequest request, MultipartFile multipartFile){
        String studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        String path = studentId + "\\middleReport\\";
        uploadService.uploadFile(multipartFile, path);
        studentService.updateStudentMiddleReport(studentId, path, multipartFile.getOriginalFilename());
    }

    @ResponseBody
    @RequestMapping("/uploadConclusionReport")
    public void uploadConclusionReport(HttpServletRequest request, MultipartFile multipartFile){
        String studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        String path = studentId + "\\conclusionReport\\";
        uploadService.uploadFile(multipartFile, path);
        studentService.updateStudentConclusionReport(studentId, path, multipartFile.getOriginalFilename());
    }

    @ResponseBody
    @RequestMapping("/uploadPaper")
    public void uploadPaper(HttpServletRequest request, MultipartFile multipartFile){
        String studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        String path = studentId + "\\paper\\";
        uploadService.uploadFile(multipartFile, path);
        studentService.updatePaper(studentId, path, multipartFile.getOriginalFilename());
    }

    @ResponseBody
    @GetMapping("/crossReview/select")//交叉评阅信息查找
    public Object select_cross_review(HttpServletRequest request){
        String student_id=((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        Crossreview crossreview=studentService.selectCrossReview(student_id);
        return Result.success(crossreview,"success");
    }

    @ResponseBody
    @PostMapping("/crossReview/score")
    public void crossScore(String studentId,int score,HttpServletRequest request){
        String reviewStudentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        studentService.updateStudentCrossPaperScore(reviewStudentId,studentId,score);
    }

}
