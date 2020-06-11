package org.t01.gdp.controller.teacher;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.t01.gdp.domain.*;
import org.t01.gdp.service.MajorService;
import org.t01.gdp.service.SubjectService;
import org.t01.gdp.service.TeacherService;
import org.t01.gdp.service.UploadService;

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


    @GetMapping("/addSubject")
    public String addSubjectGet(){
        return "teacher/addSubject";
    }

    @PostMapping("/addSubject")
    public void addSubjectPost(HttpServletRequest request, Subject subject, String major, MultipartFile file){
        //获取教师信息
        UserInfo userInfo = (UserInfo) request.getSession(true).getAttribute("USER_INFO");
        Teacher teacher = teacherService.selectTeacherById(userInfo.getId());
        //获取专业信息
        List<Major> majorList = majorService.selectMajorByName(major);
        //subject信息添加并插入
        if(majorList.size() > 0){
            subject.setMajorId(majorList.get(0).getId());
        }
        subject.setCreateTeacherId(teacher.getId());
        subject.setCreateTime(new Date());
        subject.setState("NEW");
        teacherService.addSubject(subject);

        //文件处理
        if(file != null) {
            String path = "teacher\\";
            path = path + subject.getCreateTeacherId() + "\\subjectDocuments";
            uploadService.uploadFile(file, path);
            String fileUrl = path+file.getOriginalFilename();
            subject.setDocument(fileUrl);
            subjectService.updateWithSubject(subject);
        }

    }
}
