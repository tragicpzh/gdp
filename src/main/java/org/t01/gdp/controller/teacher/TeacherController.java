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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    private static final String USER_INFO = "USER_INFO";
    private static final String LIST_FORMAT = "{\"recordsTotal\": %d,\"recordsFiltered\": %d,\"data\": %s}";

    @Autowired
    TeacherService teacherService;
    @Autowired
    MajorService majorService;
    @Autowired
    FileService fileService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    StudentService studentService;

    @GetMapping("/getMajorList")
    @ResponseBody
    public List<Major> getMajorList(HttpServletRequest request){
        Long id = ((UserInfo) request.getSession(true).getAttribute(USER_INFO)).getId();

        Teacher teacher = teacherService.selectTeacherById(id);
        return majorService.selectMajorByCollege(teacher.getCollegeId());
    }

    @PostMapping("/addSubject")
    @ResponseBody
    public void addSubjectPost(HttpServletRequest request, Subject subject, Long major, MultipartFile file) {
        //获取教师信息
        UserInfo userInfo = (UserInfo) request.getSession(true).getAttribute(USER_INFO);
        Teacher teacher = teacherService.selectTeacherById(userInfo.getId());
        subject.setMajorId(major);
        subject.setCreateTeacherId(teacher.getId());
        subject.setCreateTime(new Date());
        subject.setState("NEW");
        teacherService.addSubject(subject);

        //文件处理
        if (file != null) {
            String path = "subjectDocument/" + subject.getId() + '/';
            fileService.uploadFile(file, path);
            String fileUrl = path + file.getOriginalFilename();
            subject.setDocument(fileUrl);
            subjectService.updateWithSubject(subject);
        }
    }

    @RequestMapping("/paperReview/getList")
    @ResponseBody
    public String getPaperReviewList(int start, int length, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute(USER_INFO)).getId();
        ReviewSearchCase reviewSearchCase = new ReviewSearchCase();
        reviewSearchCase.setCreateTeacherId(teacherId);

        PageInfo<StudentAndSubject> subjectsByReviewTeacherId = subjectService.searchReview(reviewSearchCase,start/length+1,length);
        long total = subjectsByReviewTeacherId.getTotal();
        List<StudentAndSubject> list = subjectsByReviewTeacherId.getList();

        return String.format(LIST_FORMAT,total,total,list);
    }

    @RequestMapping("/paperReview/score")
    @ResponseBody
    public void paperReviewScoring(int score, String evaluation, long studentId, long subjectId, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute(USER_INFO)).getId();
        studentService.updateStudentTeacherPaperScore(teacherId, studentId, subjectId, score, evaluation);
    }

    @RequestMapping("/openReview/getList")
    @ResponseBody
    public String getOpeningReviewList(int start,int length, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute(USER_INFO)).getId();
        ReviewSearchCase reviewSearchCase = new ReviewSearchCase();
        reviewSearchCase.setOpenReviewTeacherId(teacherId);

        PageInfo<StudentAndSubject> subjectsByReviewTeacherId = subjectService.searchReview(reviewSearchCase,start/length+1,length);
        long total = subjectsByReviewTeacherId.getTotal();
        List<StudentAndSubject> list = subjectsByReviewTeacherId.getList();

        return String.format(LIST_FORMAT,total,total,list);
    }

    @PostMapping("/openReview/openScoring")
    @ResponseBody
    public void openingReviewScoring(String score, String evaluation, long studentId, long subjectId, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute(USER_INFO)).getId();
        Integer openingReviewScore = Integer.valueOf(score);
        studentService.updateStudentOpeningScore(teacherId, studentId, subjectId, openingReviewScore, evaluation);
    }

    @RequestMapping("/middleReview/getList")
    @ResponseBody
    public Object getMiddleList(int start,int length, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute(USER_INFO)).getId();
        ReviewSearchCase reviewSearchCase = new ReviewSearchCase();
        reviewSearchCase.setMiddleReviewTeacherId(teacherId);

        PageInfo<StudentAndSubject> subjectsByReviewTeacherId = subjectService.searchReview(reviewSearchCase,start/length+1,length);
        long total = subjectsByReviewTeacherId.getTotal();
        List<StudentAndSubject> list = subjectsByReviewTeacherId.getList();

        return String.format(LIST_FORMAT,total,total,list);
    }

    @PostMapping("/middleReview/middleScoring")
    @ResponseBody
    public void middleReviewScoring(int score, String evaluation, long studentId, long subjectId, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute(USER_INFO)).getId();

        studentService.updateStudentMiddleScore(teacherId, studentId, subjectId, score, evaluation);
    }

    @RequestMapping("/conclusionReview/getList")
    @ResponseBody
    public Object getConclusionReviewList(int start,int length, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute(USER_INFO)).getId();
        ReviewSearchCase reviewSearchCase = new ReviewSearchCase();
        reviewSearchCase.setConclusionReviewTeacherId(teacherId);

        PageInfo<StudentAndSubject> subjectsByReviewTeacherId = subjectService.searchReview(reviewSearchCase,start/length+1,length);
        long total = subjectsByReviewTeacherId.getTotal();
        List<StudentAndSubject> list = subjectsByReviewTeacherId.getList();

        return String.format(LIST_FORMAT,total,total,list);
    }

    @PostMapping("/conclusionReview/score")
    @ResponseBody
    public void conclusionReviewScoring(int score, String evaluation, long studentId, long subjectId, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute(USER_INFO)).getId();

        studentService.updateStudentConclusionScore(teacherId, studentId, subjectId, score, evaluation);
    }

    @RequestMapping("/searchSubject/getList")
    @ResponseBody
    public String getSubjectList(int start,int length, HttpServletRequest request){
        long id = ((UserInfo) request.getSession().getAttribute(USER_INFO)).getId();

        String searchKey = request.getParameter("search[value]");
        int orderColumn = Integer.valueOf(request.getParameter("order[0][column]"));
        String orderDirection = request.getParameter("order[0][dir]");

        String[] column = new String[]{"id","name","difficulty","major_id","direction","state"};

        if(orderColumn < 0 || orderColumn >= column.length){
            orderColumn = 0;
        }
        String orderByClause = column[orderColumn] + " " + (orderDirection.equalsIgnoreCase("desc")?"desc":"asc");

        SubjectSearchCase subjectSearchCase = new SubjectSearchCase();
        subjectSearchCase.setCreateTeacherId(id);
        subjectSearchCase.setName("%" + searchKey + "%");
        subjectSearchCase.setOrderByClause(orderByClause);

        PageInfo<Subject> subjectsByTeacherId = subjectService.searchSubjects(subjectSearchCase,start/length+1,length);
        long total = subjectsByTeacherId.getTotal();
        List<Subject> list = subjectsByTeacherId.getList();

        return String.format(LIST_FORMAT,total,total,list);
    }

    @GetMapping("/searchSubject/detail")
    @ResponseBody
    public Subject getSubjectDetail(long subjectId){
        return subjectService.getSubjectById(subjectId);
    }

    @GetMapping("/getTimeState")
    @ResponseBody
    public String getTimeState(){
        return String.valueOf(TimeAxisService.getTimeAxisState());
    }

    @PostMapping("/searchSubject/modify")
    @ResponseBody
    public void modifySubject(long id,HttpServletRequest request){
        long teacherId = ((UserInfo) request.getSession(true).getAttribute(USER_INFO)).getId();

        Subject subject = new Subject();
        subject.setId(id);
        subject.setName(request.getParameter("name"));
        subject.setMajorId(Long.valueOf(request.getParameter("majorId")));
        subject.setDirection(request.getParameter("direction"));
        subject.setDescribe(request.getParameter("describe"));
        subject.setDifficulty(Integer.valueOf(request.getParameter("difficulty")));
        subject.setTechnology(request.getParameter("technology"));
        subject.setState("MODIFIED");

        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        String subPath = null;
        String filePath = null;
        if(file != null){
            subPath = "subjectDocument/" + id + '/';
            filePath = subPath + file.getOriginalFilename();
            subject.setDocument(filePath);
        }

        if(subjectService.updateSubjectSelective(subject,teacherId) == 1 && file != null){
                fileService.uploadFile(file,subPath);
        }
    }

    @RequestMapping("/deleteFile")
    @ResponseBody
    public void deleteSubjectDocument(HttpServletRequest request,long subjectId){
        long teacherId = ((UserInfo) request.getSession(true).getAttribute(USER_INFO)).getId();

        Subject subject = subjectService.getSubjectById(subjectId);

        fileService.deleteFile(subject.getDocument());
        subject.setDocument("");

        subjectService.updateSubjectSelective(subject,teacherId);
    }

    @PostMapping("/deleteSubject")
    @ResponseBody
    public boolean deleteSubject(long subjectId){
        Subject subject = subjectService.getSubjectById(subjectId);
        fileService.deleteFile(subject.getDocument());

        return subjectService.deleteSubject(subjectId)==1;
    }

    @RequestMapping("/crossReview/getList")
    @ResponseBody
    public String getCrossingReviewList(int start,int length, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute(USER_INFO)).getId();
        ReviewSearchCase reviewSearchCase = new ReviewSearchCase();
        reviewSearchCase.setCrossReviewTeacherId(teacherId);

        PageInfo<StudentAndSubject> subjectsByReviewTeacherId = subjectService.searchReview(reviewSearchCase,start/length+1,length);
        long total = subjectsByReviewTeacherId.getTotal();
        List<StudentAndSubject> list = subjectsByReviewTeacherId.getList();

        return String.format(LIST_FORMAT,total,total,list);
    }

    @PostMapping("/getUserImage")
    @ResponseBody
    public String getUserImage(HttpServletRequest request){
        long teacherId = ((UserInfo)request.getSession(true).getAttribute(USER_INFO)).getId();
        String path = "userImage/teacher/" + teacherId + "/userimage.jpg";
        if(fileService.imageExit(path)){
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
        if(!((file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')+1)).equals("jpg"))){
            return "文件格式错误，只支持jpg文件格式";
        }
        teacherService.uploadNewUserImage(request, file);
        return "上传成功";
    }
    @PostMapping("/crossReview/score")
    @ResponseBody
    public void crossReviewScoring(int score, String evaluation, long studentId, long subjectId, HttpServletRequest request) {
        long teacherId = ((UserInfo) request.getSession(true).getAttribute(USER_INFO)).getId();

        studentService.updateStudentCrossPaperScore(teacherId, studentId, subjectId, score, evaluation);
    }

    @GetMapping("/simpleSelect")
    @ResponseBody
    public Object simpleSelect(HttpServletRequest request){
        Long teacherId=((UserInfo)(request.getSession(true).getAttribute(USER_INFO))).getId();
        return Result.success(teacherService.simpleSelect(teacherId));
    }

    @GetMapping("/ToDoList")
    @ResponseBody
    public Object todoList(HttpServletRequest request){
        Long teacherId=((UserInfo)(request.getSession(true).getAttribute(USER_INFO))).getId();
        return Result.success(teacherService.toDoList(teacherId));
    }

    @GetMapping("/getReview")
    @ResponseBody
    public Object getReview(HttpServletRequest request){
        Long teacherId=((UserInfo)(request.getSession(true).getAttribute(USER_INFO))).getId();
        return Result.success(teacherService.getReview(teacherId));
    }
}
