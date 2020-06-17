package org.t01.gdp.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.common.Result;
import org.t01.gdp.domain.Listsubject;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.domain.UserInfo;
import org.t01.gdp.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @PostMapping("/subject/choose")
    @ResponseBody
    public Object Subject_Choose(HttpServletRequest request, String subject_id2){
        String student_id=((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getRoleId();
        return Result.success(subjectService.chooseSubjectByStudent(student_id,subject_id2),"选择课题成功");
    }

    @GetMapping("/subject/select")
    @ResponseBody
    public Object subject_select( String subject_id){
        return Result.success(subjectService.selectSubjectByStudent(subject_id),"详细信息");
    }

    @GetMapping("/subject/list")
    @ResponseBody
    public Object subject_list(@RequestParam(defaultValue = "1") int pageNo,
                               @RequestParam(defaultValue = "10")int pageSize,
                               String subject_name,
                               String subject_teacher,
                               String subject_major,
                               String subject_id,
                               String subject_direction,
                               String difficult_min,
                               String difficult_max
                               ){
        return Result.success(subjectService.listSubjectByStudent(pageNo,
                pageSize,
                subject_name,
                subject_teacher,
                subject_major,
                subject_id,
                subject_direction,
                difficult_min,
                difficult_max),"课题查询");
    }
}
