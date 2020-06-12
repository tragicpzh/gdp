package org.t01.gdp.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.t01.gdp.domain.Listsubject;
import org.t01.gdp.domain.Subject;
import org.t01.gdp.service.SubjectService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @PutMapping("/subject/choose")
    public String Subject_Choose(String student_id,String subject_id){
        subjectService.chooseSubjectBystudent(student_id,subject_id);
        return "index";
    }

    @PostMapping("/subject/select")
    public String subject_select(String subject_id){
        subjectService.selectSubjectBystudent(subject_id);
        return "index";
    }

    @PostMapping("/subject/list")
    public String subject_list(Listsubject listsubject){
        List<Subject> list=subjectService.listSubjectBystudent(listsubject);
        return "index";
    }
}
