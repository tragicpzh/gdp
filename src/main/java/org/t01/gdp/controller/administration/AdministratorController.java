package org.t01.gdp.controller.administration;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.domain.*;
import org.t01.gdp.service.AdministratorService;
import org.t01.gdp.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
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

    @GetMapping("/accountManagement/accountInfoFragment")
    @ResponseBody
    public UserInfo getAccountInfo(HttpServletRequest request){
//        System.out.println((UserInfo) request.getSession(false).getAttribute("USER_INFO"));
        return (UserInfo) request.getSession(false).getAttribute("USER_INFO");
    }

//    @GetMapping("/home")
//    public String getHome(HttpServletRequest request, HttpServletResponse response, ModelMap map){
//        map.addAttribute("position","home");
//
//        return "administrator/home";
//    }

    @GetMapping("/timeAxis")
    @ResponseBody
    public ArrayList<TimePoint> getTimeAxis(ModelMap map){
        return TimeAxis.getTimePoints();
    }

    @PostMapping("/timeAxis")
    @ResponseBody
    public String setTimeAxis(TimePoint timePoint, String dateTimeString) throws ParseException {
        Date date = TimeAxis.getFormat().parse(dateTimeString);
        timePoint.setDateTime(date);

        int result = TimeAxis.setTimePoint(timePoint.getIndex(), timePoint);
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
//        states.add("RETURN");
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
}
