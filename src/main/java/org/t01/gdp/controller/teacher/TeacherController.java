package org.t01.gdp.controller.teacher;

import com.github.pagehelper.PageInfo;
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

    @PostMapping("/addSubject")
    @ResponseBody
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

        //文件处理
        if (file != null) {
            String path = "teacher\\";
            path = path + subject.getCreateTeacherId() + "\\subjectDocuments\\";
            uploadService.uploadFile(file, path);
            String fileUrl = path + file.getOriginalFilename();
            subject.setDocument(fileUrl);
        }

        teacherService.addSubject(subject);
    }

//    @GetMapping("/openingReview/getListTest")
//    @ResponseBody
//    public String test(int start,int length){
//        System.out.println("start = " + start + ", length = " + length);
//        return "{\"recordsTotal\": 11,\"recordsFiltered\": 11,\"data\": [{\"studentId\":1,\"subjectId\":1,\"subjectName\":1,\"majorId\":1,\"direction\":1,\"openDocument\":\"timeAxis.save\"},{\"studentId\":1,\"subjectId\":1,\"subjectName\":1,\"majorId\":1,\"direction\":1,\"openDocument\":\"timeAxis.save\"},{\"studentId\":1,\"subjectId\":1,\"subjectName\":1,\"majorId\":1,\"direction\":1,\"openDocument\":\"timeAxis.save\"}]}";
//    }

    @RequestMapping("/openingReview/getList")
    @ResponseBody
    public String getOpeningReviewList(int start,int length, @RequestParam("search[value]") String search, HttpServletRequest request) {

        String teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();

        PageInfo<StudentAndSubject> subjectsByReviewTeacherId = subjectService.getSubjectsByReviewTeacherId(start / length + 1, length, teacherId);
        long total = subjectsByReviewTeacherId.getTotal();
        List<StudentAndSubject> list = subjectsByReviewTeacherId.getList();

        return "{\"recordsTotal\": " + total + " ,\"recordsFiltered\": " + total + ",\"data\":" + list + "}";
    }

//    @PostMapping("/openingReview/openScoringTest")
//    @ResponseBody
//    public void test1(String score, String studentId, String subjectId){
//        System.out.println("score = " + score + ", studentId = " + studentId + ", subjectId = " + subjectId);
//    }

    @PostMapping("/openingReview/openScoring")
    @ResponseBody
    public void openingReviewScoring(String score, String studentId, String subjectId, HttpServletRequest request){
        String teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        Integer openingReviewScore = Integer.valueOf(score);
        studentService.updateStudentOpeningScore(teacherId, studentId, Long.valueOf(subjectId), openingReviewScore);
    }

    @RequestMapping("/middleReview/getList")
    @ResponseBody
    public Object getMiddleList(@RequestParam(defaultValue = "1") int pageNo,  @RequestParam(defaultValue = "10")int pageSize, HttpServletRequest request){
        String teacherId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        return Result.success(subjectService.getSubjectsByReviewTeacherId(pageNo, pageSize,teacherId), "分页查询评审列表");
    }

    @PostMapping("/middleReview/middleScoring")
    @ResponseBody
    public void middleReviewScoring(String score, String studentId, String subjectId, HttpServletRequest request){
        String teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        Integer middleReviewScore = Integer.valueOf(score);
        studentService.updateStudentMiddleScore(teacherId, studentId, Long.valueOf(subjectId), middleReviewScore);
    }

    @RequestMapping("/conclusionReview/getList")
    @ResponseBody
    public Object getConclusionList(@RequestParam(defaultValue = "1") int pageNo,  @RequestParam(defaultValue = "10")int pageSize, HttpServletRequest request){
        String teacherId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        return Result.success(subjectService.getSubjectsByReviewTeacherId(pageNo, pageSize,teacherId), "分页查询评审列表");
    }

    @PostMapping("/conclusionReview/middleScoring")
    @ResponseBody
    public void conclusionReviewScoring(String score, String studentId, String subjectId, HttpServletRequest request){
        String teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        Integer conclusionReviewScore = Integer.valueOf(score);
        studentService.updateStudentConclusionScore(teacherId, studentId, Long.valueOf(subjectId), conclusionReviewScore);
    }
}
