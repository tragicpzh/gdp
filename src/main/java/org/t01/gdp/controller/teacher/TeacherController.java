package org.t01.gdp.controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.t01.gdp.common.Result;
import org.t01.gdp.domain.*;
import org.t01.gdp.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    MajorService majorService;
    @Autowired
    UploadService uploadService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    StudentService studentService;

    @GetMapping("/addSubject")
    public String addSubjectGet() {
        return "teacher/addSubject";
    }

    @PostMapping("/addSubject")
    public void addSubjectPost(HttpServletRequest request, Subject subject, String major, MultipartFile file) {
        //获取教师信息
        UserInfo userInfo = (UserInfo) request.getSession(true).getAttribute("USER_INFO");
        Teacher teacher = teacherService.selectTeacherById(userInfo.getId());
        //获取专业信息
        List<Major> majorList = majorService.selectMajorByName(major);
        //subject信息添加并插入
        if (majorList.size() > 0) {
            subject.setMajorId(majorList.get(0).getId());
        }
        subject.setCreateTeacherId(teacher.getId());
        subject.setCreateTime(new Date());
        subject.setState("NEW");
        teacherService.addSubject(subject);

        //文件处理
        if (file != null) {
            String path = "teacher\\";
            path = path + subject.getCreateTeacherId() + "\\subjectDocuments";
            uploadService.uploadFile(file, path);
            String fileUrl = path + file.getOriginalFilename();
            subject.setDocument(fileUrl);
            subjectService.updateWithSubject(subject);
        }

    }

    @GetMapping("/openingReview")
    public String getOpeningReview() {
        return "teacher/openingReview";
    }

    @RequestMapping("/openingReview/getList")
    @ResponseBody
    public Object getOpeningReviewList(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest request) {
        String teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        return Result.success(subjectService.getSubjectsByReviewTeacherId(pageNo, pageSize, teacherId), "分页查询评审列表");
    }

    @PostMapping("/openingReview")
    public void openingReviewScoring(String score, String studentId, String subjectId, HttpServletRequest request){
        String teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        Integer openingReviewScore = Integer.valueOf(score);
        studentService.updateStudentOpeningScore(teacherId, studentId, Long.valueOf(subjectId), openingReviewScore);
    }

    @GetMapping("/middleReview")
    public String getMiddleReview(){
        return "teacher/middleReview";
    }

    @RequestMapping("/middleReview/getList")
    @ResponseBody
    public Object getMiddleList(@RequestParam(defaultValue = "1") int pageNo,  @RequestParam(defaultValue = "10")int pageSize, HttpServletRequest request){
        String teacherId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        return Result.success(subjectService.getSubjectsByReviewTeacherId(pageNo, pageSize,teacherId), "分页查询评审列表");
    }

    @PostMapping("/middleReview")
    public void middleReviewScoring(String score, String studentId, String subjectId, HttpServletRequest request){
        String teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        Integer middleReviewScore = Integer.valueOf(score);
        studentService.updateStudentMiddleScore(teacherId, studentId, Long.valueOf(subjectId), middleReviewScore);
    }

}
