package org.t01.gdp.controller.administration;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.t01.gdp.common.Result;
import org.t01.gdp.domain.*;
import org.t01.gdp.service.AdministratorService;
import org.t01.gdp.service.FileService;
import org.t01.gdp.service.SubjectService;
import org.t01.gdp.service.TimeAxisService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {
    @Autowired
    SubjectService subjectService;
    @Autowired
    AdministratorService administratorService;
    @Autowired
    FileService fileService;

    @GetMapping("/timeAxis")
    @ResponseBody
    public List<TimePoint> getTimeAxis(ModelMap map){
        return TimeAxisService.getTimePoints();
    }

    @PostMapping("/timeAxis")
    @ResponseBody
    public String setTimeAxis(TimePoint timePoint, String dateTimeString) throws ParseException {
        Date date = TimeAxisService.getFormat().parse(dateTimeString);
        timePoint.setDateTime(date);

        int result = TimeAxisService.setTimePoint(timePoint.getIndex(), timePoint);
        switch (result){
            case 0:
                return "保存成功";
            case -1:
                return "时间应在前一事件的时间之后";
            case 1:
                return "时间应在后一事件的时间之前";
            default:
                return "发生未知错误";
        }

    }

    @GetMapping("/searchSubject/getList")
    @ResponseBody
    public String getSubjectList(int start,int length, HttpServletRequest request) {
        SubjectSearchCase subjectSearchCase = new SubjectSearchCase();
        ArrayList<String> states = new ArrayList<>();
        states.add("NEW");
        states.add("MODIFIED");
        subjectSearchCase.setState(states);
        PageInfo<Subject> subjects = subjectService.searchSubjects(subjectSearchCase,start / length + 1, length);
        long total = subjects.getTotal();
        List<Subject> list = subjects.getList();

        return "{\"recordsTotal\": " + total + ",\"recordsFiltered\": " + total + ",\"data\":" + list + "}";
    }

    @GetMapping("/searchSubject/detail")
    @ResponseBody
    public Subject getSubjectDetail(long subjectId) {
        return subjectService.getSubjectById(subjectId);
    }

    @PostMapping("/subjectExamination")
    @ResponseBody
    public boolean subjectExamination(String operation, long subjectId){
        if(!operation.equals("PASSED") && !operation.equals("NEW") && !operation.equals("RETURN")){
            return false;
        }

        return subjectService.subjectExamination(operation,subjectId)==1;
    }

    @PostMapping("/searchSubject/deleteSubject")
    @ResponseBody
    public boolean deleteSubject(long subjectId) {
        return subjectService.deleteSubject(subjectId) == 1;
    }

    @PostMapping("/getUserImage")
    @ResponseBody
    public String getUserImage(HttpServletRequest request){
        long teacherId = ((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        String path = "userImage/administrator/" + teacherId + "/userimage.jpg";
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
        if(!((file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')+1)).equals("jpg"))){
            return "文件格式错误，只支持jpg文件格式";
        }
        administratorService.uploadNewUserImage(request, file);
        return "上传成功";
    }

    @GetMapping("/simpleSelect")
    @ResponseBody
    public Object simpleSelect(HttpServletRequest request){
        Long admin_id=((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        return Result.success(administratorService.simpleSelect(admin_id));
    }

    @GetMapping("/ToDoList")
    @ResponseBody
    public Object ToDoList(HttpServletRequest request){
        Long admin_id=((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        return  Result.success(administratorService.ToDoList(admin_id));
    }

    @GetMapping("/getLable")
    @ResponseBody
    public Object getLable(){
        return Result.success(administratorService.getLable());
    }
}
