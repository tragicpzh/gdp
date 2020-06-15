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

    @RequestMapping("/accountInfo")
    public String getAccountInfo() {
        return "/accountInfo";
    }

    @GetMapping("/addSubject")
    public String addSubjectGet() {
        return "teacher/addSubject";
    }

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

        teacherService.addSubject(subject);
    }

    @GetMapping("/openingReview")
    public String getOpeningReview() {
        return "teacher/openingReview";
    }

    @RequestMapping("/paperReview/getList")
    @ResponseBody
    public String getPaperReviewList(int start,int length, @RequestParam("search[value]") String search, HttpServletRequest request){
        String teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();

        PageInfo<PaperReviewInfo> subjectsToPaperReview = subjectService.getSubjectsToPaperReview(start / length + 1, length, teacherId);
        long total = subjectsToPaperReview.getTotal();
        List<PaperReviewInfo> list = subjectsToPaperReview.getList();

        return "{\"recordsTotal\": " + total + " ,\"recordsFiltered\": " + total + ",\"data\":" + list.toString() + "}";
    }

    @RequestMapping("/paperReview/score")
    @ResponseBody
    public void paperReviewScoring(String score, String studentId, String subjectId, HttpServletRequest request){
        String teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        Integer openingReviewScore = Integer.valueOf(score);
        studentService.updateStudentTeacherPaperScore(teacherId, studentId, Long.valueOf(subjectId), openingReviewScore);
    }

//    @GetMapping("/openingReview/getListTest")
//    @ResponseBody
//    public String test(int start,int length){
//        System.out.println("start = " + start + ", length = " + length);
//        return "{\"recordsTotal\": 11,\"recordsFiltered\": 11,\"data\": [{\"studentId\":1,\"subjectId\":1,\"subjectName\":1,\"majorId\":1,\"direction\":1,\"openDocument\":\"timeAxis.save\"},{\"studentId\":1,\"subjectId\":1,\"subjectName\":1,\"majorId\":1,\"direction\":1,\"openDocument\":\"timeAxis.save\"},{\"studentId\":1,\"subjectId\":1,\"subjectName\":1,\"majorId\":1,\"direction\":1,\"openDocument\":\"timeAxis.save\"}]}";
//    }

    @RequestMapping("/openingReview/getList")
    @ResponseBody
    public String getOpeningReviewList(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest request) {
        String teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        PageInfo<StudentAndSubject> subjectsByReviewTeacherId = subjectService.getSubjectsByReviewTeacherId(pageNo, pageSize, teacherId);
        long total = subjectsByReviewTeacherId.getTotal();
        List<StudentAndSubject> list = subjectsByReviewTeacherId.getList();

        return "{\"recordsTotal\": " + total + ",\"recordsFiltered\": " + total + ",\"data\":" + list + "}";
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
    @GetMapping("/subjectFragment")
    public String getSubjectFragment(){
        System.out.println("@GetMapping(\"/subjectFragment\")");
        return "teacher/subjectFragment";
    }
    @PostMapping("/subjectFragment")
    public void setSubjectFragment(String score, String studentId, String subjectId, HttpServletRequest request){
        System.out.println("@PostMapping(\"/subjectFragment\")");
        String teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        Integer middleReviewScore = Integer.valueOf(score);
        studentService.updateStudentMiddleScore(teacherId, studentId, Long.valueOf(subjectId), middleReviewScore);
    }

    @RequestMapping("/subjectFragment/getList")
    @ResponseBody
    public Object getSubjectList(@RequestParam(defaultValue = "1") int pageNo,  @RequestParam(defaultValue = "10")int pageSize, HttpServletRequest request){
        System.out.println("@RequestMapping(\"/subjectFragment/getList\")");
        String teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        System.out.println(teacherId);
        return Result.success(subjectService.getSubjectsByTeacherId(pageNo, pageSize, teacherId), "分页查询项目列表");
    }


}
