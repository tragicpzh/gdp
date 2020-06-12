package org.t01.gdp.service;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.Teacher;
import org.t01.gdp.domain.User;
import org.t01.gdp.domain.UserInfo;
import org.t01.gdp.mapper.SqlMapper;
import org.t01.gdp.mapper.StudentMapper;
import org.t01.gdp.mapper.TeacherMapper;
import org.t01.gdp.mapper.UserMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;
    private final SqlMapper sqlMapper;

    public UserInfo getUserInfo(String user_id) {
        User user = userMapper.selectByPrimaryKey(user_id);
        return new UserInfo(user.getId(), user.getRole(), user.getName(), user.getPhoneNumber(), user.getEmail(), user.getCreateTime());
    }

    public boolean addUser(User user, String otherInfo) {
        boolean resultFlag = userMapper.insertSelective(user) == 1;
        if (resultFlag) {
            if (user.getRole().equals("TEA")) {
                Teacher teacher = new Teacher();
                teacher.setId(user.getId());
                teacher.setCollegeId(otherInfo);
                resultFlag = teacherMapper.insertSelective(teacher) == 1;
            } else if (user.getRole().equals("STU")) {
                Student student = new Student();
                student.setId(user.getId());
                student.setMajorId(otherInfo);
                resultFlag = studentMapper.insertSelective(student) == 1;
            }
        }
        return resultFlag;
    }

    public boolean updateUser(User user, String otherInfo) {
        boolean resultFlag = userMapper.updateByPrimaryKeySelective(user) == 1;
        if (resultFlag) {
            if (user.getRole().equals("TEA")) {
                Teacher teacher = new Teacher();
                teacher.setId(user.getId());
                teacher.setCollegeId(otherInfo);
                resultFlag = teacherMapper.updateByPrimaryKeySelective(teacher) == 1;
            } else if (user.getRole().equals("STU")) {
                Student student = new Student();
                student.setId(user.getId());
                student.setMajorId(otherInfo);
                resultFlag = studentMapper.updateByPrimaryKeySelective(student) == 1;
            }
        }
        return resultFlag;
    }

    public boolean deleteUserById(String id) {
        String role = userMapper.selectByPrimaryKey(id).getRole();
        boolean resultFlag = false;
        if (role.equals("TEA")) {
            resultFlag = teacherMapper.deleteByPrimaryKey(id) == 1;
        } else if (role.equals("STU")) {
            resultFlag = studentMapper.deleteByPrimaryKey(id) == 1;
        }

        if (resultFlag) {
            resultFlag = userMapper.deleteByPrimaryKey(id) == 1;
        }
        return resultFlag;
    }

    public List<HashMap<String, Object>> getUserByRole(String role) {
        return sqlMapper.getUserByRole(role);
    }
}
