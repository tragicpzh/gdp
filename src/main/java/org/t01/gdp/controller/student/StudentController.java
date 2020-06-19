package org.t01.gdp.controller.student;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.domain.*;
import org.springframework.web.multipart.MultipartFile;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.domain.TimeAxis;
import org.t01.gdp.domain.UserInfo;
import org.t01.gdp.service.FileService;
import org.t01.gdp.service.StudentService;
import org.t01.gdp.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    FileService fileService;
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
//    @RequestMapping("/getTimeState")
//    public String getTimeState() {
//        return String.valueOf(TimeAxis.getTimeAxisState());
//    }

//    @ResponseBody
//    @RequestMapping("/getStudentInfo")
//    public Student getStudentInfo(@RequestParam(name = "id") String id) {
//        return studentService.getStudentInfoById(id);
//    }

//    @ResponseBody
//    @RequestMapping("/upgradeState")
//    public String upgradeState(Student student) {
//        return studentService.updateState(student);
//    }

//    @ResponseBody
//    @RequestMapping("/getSubject")
//    public Subject getSubject(@RequestParam(name = "id") long id) {
//        return studentService.getSubjectById(id);
//    }

//    @ResponseBody
//    @PostMapping("/crowssreview")
//    public boolean cross_review_create(List<Student> list){
//        studentService.cross_review_create(list);
//        return true;
//    }

    @ResponseBody
    @RequestMapping("/uploadOpenReport")
    public void uploadOpenReport(HttpServletRequest request, MultipartFile multipartFile){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        String path ="studentDocument/" + studentId + "/openReport/";
        fileService.uploadFile(multipartFile, path);
        studentService.updateStudentOpenReport(studentId, path, multipartFile.getOriginalFilename());
    }

    @RequestMapping("/deleteOpenReport")
    public void deleteOpenReport(HttpServletRequest request, MultipartFile multipartFile){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        Student student = studentService.getStudent(studentId);
        fileService.deleteFile(student.getOpenDocument());

        studentService.updateStudentOpenReport(studentId,"","");
    }

    @ResponseBody
    @RequestMapping("/uploadMiddleReport")
    public void uploadMiddleReport(HttpServletRequest request, MultipartFile multipartFile){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        String path ="studentDocument/" + studentId + "/middleReport/";
        fileService.uploadFile(multipartFile, path);
        studentService.updateStudentMiddleReport(studentId, path, multipartFile.getOriginalFilename());
    }

    @RequestMapping("/deleteMiddleReport")
    public void deleteMiddleReport(HttpServletRequest request, MultipartFile multipartFile){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        Student student = studentService.getStudent(studentId);
        fileService.deleteFile(student.getMiddleDocument());

        studentService.updateStudentMiddleReport(studentId,"","");
    }

    @ResponseBody
    @RequestMapping("/uploadConclusionReport")
    public void uploadConclusionReport(HttpServletRequest request, MultipartFile multipartFile){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        String path ="studentDocument/" + studentId + "/conclusionReport/";
        fileService.uploadFile(multipartFile, path);
        studentService.updateStudentConclusionReport(studentId, path, multipartFile.getOriginalFilename());
    }

    @RequestMapping("/deleteConclusionReport")
    public void deleteConclusionReport(HttpServletRequest request, MultipartFile multipartFile){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        Student student = studentService.getStudent(studentId);
        fileService.deleteFile(student.getConclusionDocument());

        studentService.updateStudentConclusionReport(studentId,"","");
    }

    @ResponseBody
    @RequestMapping("/uploadPaper")
    public void uploadPaper(HttpServletRequest request, MultipartFile multipartFile){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        String path ="studentDocument/" + studentId + "/paper/";
        fileService.uploadFile(multipartFile, path);
        studentService.updatePaper(studentId, path, multipartFile.getOriginalFilename());
    }

    @RequestMapping("/deletePaper")
    public void deletePaper(HttpServletRequest request, MultipartFile multipartFile){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        Student student = studentService.getStudent(studentId);
        fileService.deleteFile(student.getPaperDocument());

        studentService.updatePaper(studentId,"","");
    }
}
