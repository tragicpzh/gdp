package org.t01.gdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.AdministratorMapper;
import org.t01.gdp.mapper.StudentMapper;
import org.t01.gdp.mapper.TeacherMapper;

import java.util.List;

@Service

public class UserService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    AdministratorMapper administratorMapper;

    public boolean setPassword(String username, String password, String role){
        if(role.equals("STU")){
            StudentExample studentExample = new StudentExample();
            studentExample.createCriteria().andStudentIdEqualTo(username);

            Student student = new Student();
            student.setPassword(password);

            return studentMapper.updateByExampleSelective(student,studentExample)==1;
        }else if(role.equals("TEA")){
            TeacherExample teacherExample = new TeacherExample();
            teacherExample.createCriteria().andTeacherIdEqualTo(username);

            Teacher teacher = new Teacher();
            teacher.setPassword(password);

            return teacherMapper.updateByExampleSelective(teacher,teacherExample)==1;
        }else if(role.equals("ADM")){
            AdministratorExample administratorExample = new AdministratorExample();
            administratorExample.createCriteria().andAdminIdEqualTo(username);

            Administrator administrator = new Administrator();
            administrator.setPassword(password);

            return administratorMapper.updateByExampleSelective(administrator,administratorExample)==1;
        }
        return false;
    }

    public String getEmail(String username, String role){
        if(role.equals("STU")){
            StudentExample studentExample = new StudentExample();
            studentExample.createCriteria().andStudentIdEqualTo(username);
            List<Student> students = studentMapper.selectByExample(studentExample);
            if(!students.isEmpty()){
                return students.get(0).getEmail();
            }
        }else if(role.equals("TEA")){
            TeacherExample teacherExample = new TeacherExample();
            teacherExample.createCriteria().andTeacherIdEqualTo(username);
            List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
            if(!teachers.isEmpty()){
                return teachers.get(0).getEmail();
            }
        }else if(role.equals("ADM")){
            AdministratorExample administratorExample = new AdministratorExample();
            administratorExample.createCriteria().andAdminIdEqualTo(username);
            List<Administrator> administrators = administratorMapper.selectByExample(administratorExample);
            if(!administrators.isEmpty()){
                return administrators.get(0).getEmail();
            }
        }
        return null;
    }

    public String getPhoneNumber(String username, String role){
        if(role.equals("STU")){
            StudentExample studentExample = new StudentExample();
            studentExample.createCriteria().andStudentIdEqualTo(username);
            List<Student> students = studentMapper.selectByExample(studentExample);
            if(!students.isEmpty()){
                return students.get(0).getPhoneNumber();
            }
        }else if(role.equals("TEA")){
            TeacherExample teacherExample = new TeacherExample();
            teacherExample.createCriteria().andTeacherIdEqualTo(username);
            List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
            if(!teachers.isEmpty()){
                return teachers.get(0).getPhoneNumber();
            }
        }else if(role.equals("ADM")){
            AdministratorExample administratorExample = new AdministratorExample();
            administratorExample.createCriteria().andAdminIdEqualTo(username);
            List<Administrator> administrators = administratorMapper.selectByExample(administratorExample);
            if(!administrators.isEmpty()){
                return administrators.get(0).getPhoneNumber();
            }
        }
        return null;
    }
}
