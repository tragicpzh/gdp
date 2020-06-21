package org.t01.gdp.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.t01.gdp.common.Result;
import org.t01.gdp.domain.UserInfo;
import org.t01.gdp.service.SubjectService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @PostMapping("/subject/choose")
    @ResponseBody
    public Object subjectChoose(HttpServletRequest request, @RequestParam("subject_id2") String subjectId){
        Long studentId=((UserInfo)request.getSession(true).getAttribute("USER_INFO")).getId();
        return Result.success(subjectService.chooseSubjectByStudent(studentId,subjectId));
    }

    @GetMapping("/subject/select")
    @ResponseBody
    public Object subjectSelect(@RequestParam("subject_id") String subjectId){
        return Result.success(subjectService.selectSubjectByStudent(subjectId),"详细信息");
    }

    @GetMapping("/subject/list")
    @ResponseBody
    public Object getSubjectList(@RequestParam(defaultValue = "1") int pageNo,
                                 @RequestParam(defaultValue = "10")int pageSize,
                                 String subject_name,
                                 String subject_teacher,
                                 String subject_major,
                                 String subject_id,
                                 String subject_direction,
                                 String difficult_min,
                                 String difficult_max
    ){
        return Result.success(subjectService.listSubjectByCondition(pageNo,
                pageSize,
                subject_name,
                subject_teacher,
                subject_major,
                subject_id,
                subject_direction,
                difficult_min,
                difficult_max,
                "PASSED"),"课题查询");
    }
}
