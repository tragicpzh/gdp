package org.t01.gdp.controller.student;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.common.Result;
import org.t01.gdp.domain.*;
import org.springframework.web.multipart.MultipartFile;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.domain.UserInfo;
import org.t01.gdp.service.FileService;
import org.t01.gdp.service.StudentService;
import org.t01.gdp.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String getSubjectList(int pageNo, int pageSize, HttpServletRequest request){
        SubjectSearchCase subjectSearchCase = new SubjectSearchCase();
        ArrayList<String> states = new ArrayList<>();
        states.add("PASSED");
        subjectSearchCase.setState(states);

        PageInfo<Subject> subjectsByTeacherId = subjectService.searchSubjects(subjectSearchCase,pageNo,pageSize);
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


    @RequestMapping("/uploadOpen")
    @ResponseBody
    public void uploadOpenReport(HttpServletRequest request, MultipartFile multipartFile){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        String path ="studentDocument/" + studentId + "/openReport/";
        fileService.uploadFile(multipartFile, path);
        studentService.updateStudentOpenReport(studentId, path, multipartFile.getOriginalFilename());
    }

    @RequestMapping("/deleteOpen")
    @ResponseBody
    public void deleteOpenReport(HttpServletRequest request, MultipartFile multipartFile){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        Student student = studentService.getStudent(studentId);
        fileService.deleteFile(student.getOpenDocument());

        studentService.updateStudentOpenReport(studentId,"","");
    }

    @RequestMapping("/uploadMiddle")
    @ResponseBody
    public void uploadMiddleReport(HttpServletRequest request, MultipartFile multipartFile){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        String path ="studentDocument/" + studentId + "/middleReport/";
        fileService.uploadFile(multipartFile, path);
        studentService.updateStudentMiddleReport(studentId, path, multipartFile.getOriginalFilename());
    }

    @RequestMapping("/deleteMiddle")
    @ResponseBody
    public void deleteMiddleReport(HttpServletRequest request){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        Student student = studentService.getStudent(studentId);
        fileService.deleteFile(student.getMiddleDocument());

        studentService.updateStudentMiddleReport(studentId,"","");
    }

    @RequestMapping("/uploadConclusion")
    @ResponseBody
    public void uploadConclusionReport(HttpServletRequest request, MultipartFile multipartFile){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        String path ="studentDocument/" + studentId + "/conclusionReport/";
        fileService.uploadFile(multipartFile, path);
        studentService.updateStudentConclusionReport(studentId, path, multipartFile.getOriginalFilename());
    }

    @RequestMapping("/deleteConclusion")
    @ResponseBody
    public void deleteConclusionReport(HttpServletRequest request){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        Student student = studentService.getStudent(studentId);
        fileService.deleteFile(student.getConclusionDocument());

        studentService.updateStudentConclusionReport(studentId,"","");
    }

    @RequestMapping("/uploadPaper")
    @ResponseBody
    public void uploadPaper(HttpServletRequest request, MultipartFile multipartFile){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        String path ="studentDocument/" + studentId + "/paper/";
        fileService.uploadFile(multipartFile, path);
        studentService.updatePaper(studentId, path, multipartFile.getOriginalFilename());
    }

    @RequestMapping("/deletePaper")
    @ResponseBody
    public void deletePaper(HttpServletRequest request){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        Student student = studentService.getStudent(studentId);
        fileService.deleteFile(student.getPaperDocument());

        studentService.updatePaper(studentId,"","");
    }

    @RequestMapping("/getAllFilePath")
    @ResponseBody
    public Map<String,String> getAllFilePath(HttpServletRequest request){
        long studentId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        Student student = studentService.getStudent(studentId);

        Map<String,String> paths = new HashMap<>();
        paths.put("open",student.getOpenDocument());
        paths.put("middle",student.getMiddleDocument());
        paths.put("paper",student.getPaperDocument());
        paths.put("conclusion",student.getConclusionDocument());

        return paths;
    }

    @GetMapping("/simpleSelect")
    @ResponseBody
    public Object simpleSelect(HttpServletRequest request){
        Long student_id=((UserInfo)(request.getSession(true).getAttribute("USER_INFO"))).getId();
        return Result.success(studentService.simpleSelect(student_id));
    }

    @GetMapping("/ToDoList")
    @ResponseBody
    public Object ToDoList(HttpServletRequest request){
        Long student_id=((UserInfo)(request.getSession(true).getAttribute("USER_INFO"))).getId();
        return Result.success(studentService.ToDoList(student_id));
    }

    @GetMapping("/getScore")
    @ResponseBody
    public Object getScore(HttpServletRequest request){
        Long student_id=((UserInfo)(request.getSession(true).getAttribute("USER_INFO"))).getId();
        return Result.success(studentService.getScore(student_id));
    }

    @GetMapping("/getSubject")
    @ResponseBody
    public Object getSubject(HttpServletRequest request){
        Long student_id=((UserInfo)(request.getSession(true).getAttribute("USER_INFO"))).getId();
        return Result.success(studentService.getSubject(student_id));
    }

    @PostMapping("/getUserImage")
    @ResponseBody
    public String getUserImage(HttpServletRequest request){
        long teacherId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        String path = "userImage/student/" + String.valueOf(teacherId) + "/userimage.jpg";
        if(fileService.fileExit(path)){
            return "../" + path;
        }
        return "../Rendering/dist/img/user2-160x160.jpg";
    }

    @PostMapping("/uploadNewUserImage")
    @ResponseBody
    public String uploadNewUserImage(HttpServletRequest request, MultipartFile file){
        if(file == null){
            return "请选择文件";
        }
        if(!((file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1)).equals("jpg"))){
            return "文件格式错误，只支持jpg文件格式";
        }
        studentService.uploadNewUserImage(request, file);
        return "上传成功";
    }
}
