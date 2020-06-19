package org.t01.gdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.AdministratorMapper;
import org.t01.gdp.mapper.StudentMapper;
import org.t01.gdp.mapper.TeacherMapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class NoticeService {
    @Autowired
    MailService mailService;
    @Autowired
    AdministratorMapper administratorMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    StudentMapper studentMapper;

    public void sendMessageNotice(){
        String messageNotice = TimeAxisService.getNowTimePoint().getMessageNotice();

        String[] notices = messageNotice.split("\n");

        for(String notice:notices){
            if(notice.startsWith("@ADM")){
                sendMessageNoticeToAdministrators(notice.substring(5));
            }else if(notice.startsWith("@TEA")){
                sendMessageNoticeToTeacher(notice.substring(5));
            }else if(notice.startsWith("@STU")){
                sendMessageNoticeToStudent(notice.substring(5));
            }
        }
    }

    public void sendMessageNoticeToTeacher(String notice){
        List<String> mailAddresses = new ArrayList<>();
        List<Teacher> teachers = teacherMapper.selectByExample(new TeacherExample());

        Iterator<Teacher> iterator = teachers.iterator();
        while (iterator.hasNext()){
            Teacher next = iterator.next();
            if(next.getEmail()!=null){
                mailAddresses.add(next.getEmail());
                System.out.println(next.getEmail());
                System.out.println("TEA_notice = " + notice);
            }
        }

        mailService.patchSendSingleMessage(notice,mailAddresses);
    }

    public void sendMessageNoticeToStudent(String notice){
        List<String> mailAddresses = new ArrayList<>();
        List<Student> students = studentMapper.selectByExample(new StudentExample());

        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()){
            Student next = iterator.next();
            if(next.getEmail()!=null){
                mailAddresses.add(next.getEmail());
                System.out.println(next.getEmail());
                System.out.println("STU_notice = " + notice);
            }
        }

        mailService.patchSendSingleMessage(notice,mailAddresses);
    }

    public void sendMessageNoticeToAdministrators(String notice){
        List<String> mailAddresses = new ArrayList<>();
        List<Administrator> administrators = administratorMapper.selectByExample(new AdministratorExample());

        Iterator<Administrator> iterator = administrators.iterator();
        while (iterator.hasNext()){
            Administrator next = iterator.next();
            if(next.getEmail()!=null){
                mailAddresses.add(next.getEmail());
                System.out.println(next.getEmail());
                System.out.println("ADM_notice = " + notice);
            }
        }

        mailService.patchSendSingleMessage(notice,mailAddresses);
    }
}
