package org.t01.gdp.service;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.t01.gdp.domain.College;
import org.t01.gdp.domain.Major;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.Teacher;
import org.t01.gdp.mapper.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final MajorMapper majorMapper;
    private final CollegeMapper collegeMapper;
    private final UserMapper userMapper;
    private static Set<String> studentIds;
    private static Set<String> teacherIds;

    private String getRandomVal() {
        StringBuilder randomVal = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            randomVal.append(random.nextInt(10));
        }
        return randomVal.toString();
    }

    public String addUser(String name, String phoneNumber, String email, String role, String otherInfo) {
        if (studentIds.size() == 0) {
            studentIds = userMapper.getAllStudentId();
        }
        if (teacherIds.size() == 0) {
            teacherIds = userMapper.getAllTeacherId();
        }
        Calendar calendar = Calendar.getInstance();
        String id = String.valueOf(calendar.get(Calendar.YEAR));
        String randomVal = getRandomVal();
        if (role.equals("TEA")) {
            College college = collegeMapper.selectByPrimaryKey(Long.valueOf(otherInfo));
            if (college == null) {
                return "No Such College";
            }
            id += String.valueOf(college.getId());

            while (teacherIds.contains(id + randomVal)) {
                randomVal = getRandomVal();
            }

            id += randomVal;
            Teacher teacher = new Teacher();
            teacher.setTeacherId(id);
            teacher.setPassword(randomVal);
            teacher.setName(name);
            teacher.setPhoneNumber(phoneNumber);
            teacher.setEmail(email);
            teacher.setCollegeId(Long.valueOf(otherInfo));
            teacherMapper.insertSelective(teacher);
            teacherIds.add(id);
        } else if (role.equals("STU")) {
            Major major = majorMapper.selectByPrimaryKey(Long.valueOf(otherInfo));
            if (major == null) {
                return "No Such Major";
            }
            id += major.getCollegeId() + String.valueOf(major.getId());
            while (studentIds.contains(id + randomVal)) {
                randomVal = getRandomVal();
            }
            id += randomVal;
            Student student = new Student();
            student.setStudentId(id);
            student.setPassword(randomVal);
            student.setName(name);
            student.setPhoneNumber(phoneNumber);
            student.setEmail(email);
            student.setMajorId(Long.valueOf(otherInfo));
            studentMapper.insertSelective(student);
            studentIds.add(id);
        }
        return id + " success";
    }

    public List<Map<String, String>> getUserByRole(String role) {
        if (role.equals("TEA")) {
            return userMapper.getAllTeachers();
        } else if (role.equals("STU")) {
            return userMapper.getAllStudents();
        }
        return null;
    }
}
