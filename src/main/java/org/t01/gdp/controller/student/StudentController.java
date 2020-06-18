package org.t01.gdp.controller.student;

import com.github.pagehelper.PageInfo;
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
import org.t01.gdp.service.SubjectService;
import org.t01.gdp.service.UploadService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    UploadService uploadService;
    @Autowired
    SubjectService subjectService;

    @GetMapping("selectSubject/getList")
    @ResponseBody
    public String getSubjectList(int start,int length, HttpServletRequest request){
        SubjectSearchCase subjectSearchCase = new SubjectSearchCase();
        ArrayList<String> states = new ArrayList<>();
        states.add("PASSED");
        subjectSearchCase.setState(states);

        PageInfo<Subject> subjectsByTeacherId = subjectService.searchSubjects(subjectSearchCase,start/length+1,length);
        long total = subjectsByTeacherId.getTotal();
        List<Subject> list = subjectsByTeacherId.getList();

        return "{\"recordsTotal\": " + total + ",\"recordsFiltered\": " + total + ",\"data\":" + list + "}";
    }

    @GetMapping("/selectSubject/detail")
    @ResponseBody
    public Subject getSubjectDetail(long subjectId){
        return subjectService.getSubjectById(subjectId);
    }

//    @ResponseBody
//    @PostMapping("/crowssreview")
//    public boolean cross_review_create(List<Student> list){
//        studentService.cross_review_create(list);
//        return true;
//    }

    @ResponseBody
    @RequestMapping("/uploadOpenReport")
    public void uploadOpenReport(HttpServletRequest request, MultipartFile multipartFile){
        String studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getRoleId();
        String path ="student\\" + studentId + "\\openReport\\";
        uploadService.uploadFile(multipartFile, path);
        studentService.updateStudentOpenReport(studentId, path, multipartFile.getOriginalFilename());
    }

    @ResponseBody
    @RequestMapping("/uploadMiddleReport")
    public void uploadMiddleReport(HttpServletRequest request, MultipartFile multipartFile){
        String studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getRoleId();
        String path ="student\\" + studentId + "\\middleReport\\";
        uploadService.uploadFile(multipartFile, path);
        studentService.updateStudentMiddleReport(studentId, path, multipartFile.getOriginalFilename());
    }

    @ResponseBody
    @RequestMapping("/uploadConclusionReport")
    public void uploadConclusionReport(HttpServletRequest request, MultipartFile multipartFile){
        String studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getRoleId();
        String path ="student\\" + studentId + "\\conclusionReport\\";
        uploadService.uploadFile(multipartFile, path);
        studentService.updateStudentConclusionReport(studentId, path, multipartFile.getOriginalFilename());
    }

    @ResponseBody
    @RequestMapping("/uploadPaper")
    public void uploadPaper(HttpServletRequest request, MultipartFile multipartFile){
        String studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getRoleId();
        String path ="student\\" + studentId + "\\paper\\";
        uploadService.uploadFile(multipartFile, path);
        studentService.updatePaper(studentId, path, multipartFile.getOriginalFilename());
    }
}
