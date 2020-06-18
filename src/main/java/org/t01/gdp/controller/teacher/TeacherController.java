package org.t01.gdp.controller.teacher;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.t01.gdp.common.Result;
import org.t01.gdp.domain.*;
import org.t01.gdp.service.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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
        teacherService.addSubject(subject);

        //文件处理
        if (file != null) {
            String path = "teacher\\" + subject.getCreateTeacherId() + "\\subjectDocuments\\" + subject.getId() + "\\";
            uploadService.uploadFile(file, path);
            String fileUrl = path + file.getOriginalFilename();
            subject.setDocument(fileUrl);
            subjectService.updateWithSubject(subject);
        }
    }

    @RequestMapping("/paperReview/getList")
    @ResponseBody
    public String getPaperReviewList(int start, int length, @RequestParam("search[value]") String search, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        ReviewSearchCase reviewSearchCase = new ReviewSearchCase();
        reviewSearchCase.setPaperReviewTeacherId(teacherId);

        PageInfo<StudentAndSubject> subjectsByReviewTeacherId = subjectService.searchReview(reviewSearchCase,start/length+1,length);
        long total = subjectsByReviewTeacherId.getTotal();
        List<StudentAndSubject> list = subjectsByReviewTeacherId.getList();

        return "{\"recordsTotal\": " + total + ",\"recordsFiltered\": " + total + ",\"data\":" + list + "}";
    }

    @RequestMapping("/paperReview/score")
    @ResponseBody
    public void paperReviewScoring(int score, long studentId, long subjectId, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        studentService.updateStudentTeacherPaperScore(teacherId, studentId, subjectId, score);
    }

//    @GetMapping("/openingReview/getListTest")
//    @ResponseBody
//    public String test(int start,int length){
//        System.out.println("start = " + start + ", length = " + length);
//        return "{\"recordsTotal\": 11,\"recordsFiltered\": 11,\"data\": [{\"studentId\":1,\"subjectId\":1,\"subjectName\":1,\"majorId\":1,\"direction\":1,\"openDocument\":\"timeAxis.save\"},{\"studentId\":1,\"subjectId\":1,\"subjectName\":1,\"majorId\":1,\"direction\":1,\"openDocument\":\"timeAxis.save\"},{\"studentId\":1,\"subjectId\":1,\"subjectName\":1,\"majorId\":1,\"direction\":1,\"openDocument\":\"timeAxis.save\"}]}";
//    }

    @RequestMapping("/openReview/getList")
    @ResponseBody
    public String getOpeningReviewList(int start,int length, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        ReviewSearchCase reviewSearchCase = new ReviewSearchCase();
        reviewSearchCase.setOpenReviewTeacherId(teacherId);

        PageInfo<StudentAndSubject> subjectsByReviewTeacherId = subjectService.searchReview(reviewSearchCase,start/length+1,length);
        long total = subjectsByReviewTeacherId.getTotal();
        List<StudentAndSubject> list = subjectsByReviewTeacherId.getList();

        return "{\"recordsTotal\": " + total + ",\"recordsFiltered\": " + total + ",\"data\":" + list + "}";
    }

    @PostMapping("/openReview/openScoring")
    @ResponseBody
    public void openingReviewScoring(String score, long studentId, long subjectId, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        Integer openingReviewScore = Integer.valueOf(score);
        studentService.updateStudentOpeningScore(teacherId, studentId, subjectId, openingReviewScore);
    }

    @RequestMapping("/middleReview/getList")
    @ResponseBody
    public Object getMiddleList(int start,int length, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        ReviewSearchCase reviewSearchCase = new ReviewSearchCase();
        reviewSearchCase.setMiddleReviewTeacherId(teacherId);

        PageInfo<StudentAndSubject> subjectsByReviewTeacherId = subjectService.searchReview(reviewSearchCase,start/length+1,length);
        long total = subjectsByReviewTeacherId.getTotal();
        List<StudentAndSubject> list = subjectsByReviewTeacherId.getList();

        return "{\"recordsTotal\": " + total + ",\"recordsFiltered\": " + total + ",\"data\":" + list + "}";
    }

    @PostMapping("/middleReview/middleScoring")
    public void middleReviewScoring(int score, long studentId, long subjectId, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();

        studentService.updateStudentMiddleScore(teacherId, studentId, subjectId, score);
    }

    @RequestMapping("/conclusionReview/getList")
    @ResponseBody
    public Object getConclusionReviewList(int start,int length, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();
        ReviewSearchCase reviewSearchCase = new ReviewSearchCase();
        reviewSearchCase.setConclusionReviewTeacherId(teacherId);

        PageInfo<StudentAndSubject> subjectsByReviewTeacherId = subjectService.searchReview(reviewSearchCase,start/length+1,length);
        long total = subjectsByReviewTeacherId.getTotal();
        List<StudentAndSubject> list = subjectsByReviewTeacherId.getList();

        return "{\"recordsTotal\": " + total + ",\"recordsFiltered\": " + total + ",\"data\":" + list + "}";
    }

    @PostMapping("/conclusionReview/score")
    @ResponseBody
    public void conclusionReviewScoring(int score, long studentId, long subjectId, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();

        studentService.updateStudentMiddleScore(teacherId, studentId, subjectId, score);
    }

    @RequestMapping("/searchSubject/getList")
    @ResponseBody
    public String getSubjectList(int start,int length, HttpServletRequest request){
        long id = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();

        SubjectSearchCase subjectSearchCase = new SubjectSearchCase();
        subjectSearchCase.setCreateTeacherId(id);

        PageInfo<Subject> subjectsByTeacherId = subjectService.searchSubjects(subjectSearchCase,start/length+1,length);
        long total = subjectsByTeacherId.getTotal();
        List<Subject> list = subjectsByTeacherId.getList();

        return "{\"recordsTotal\": " + total + ",\"recordsFiltered\": " + total + ",\"data\":" + list + "}";
    }

    @GetMapping("/searchSubject/detail")
    @ResponseBody
    public Subject getSubjectDetail(long subjectId){
        return subjectService.getSubjectById(subjectId);
    }

    @PostMapping("/searchSubject/modify")
    @ResponseBody
    public void modifySubject(long id,HttpServletRequest request){
        long teacherId = ((UserInfo) request.getSession(true).getAttribute("USER_INFO")).getId();

        Subject subject = new Subject();
        subject.setId(id);
        subject.setName(request.getParameter("name"));
        subject.setMajorId(Long.valueOf(request.getParameter("majorId")));
        subject.setDirection(request.getParameter("direction"));
        subject.setDescribe(request.getParameter("describe"));
        subject.setDifficulty(Integer.valueOf(request.getParameter("difficulty")));
        subject.setTechnology(request.getParameter("technology"));

        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        String subPath = null,filePath = null;
        if(file != null){
            subPath = teacherId + "\\" + id + "\\";
            filePath = subPath + file.getOriginalFilename();
            subject.setDocument(filePath);
        }

        if(subjectService.updateSubjectSelective(subject,teacherId) == 1){
            if(file != null){
                uploadService.uploadFile(file,subPath);
            }
        }
    }

    @PostMapping("/deleteSubject")
    @ResponseBody
    public boolean deleteSubject(long subjectId){

        return subjectService.deleteSubject(subjectId)==1;
    }

}
