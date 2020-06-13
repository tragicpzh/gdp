package org.t01.gdp.service;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;
    private final AdministratorMapper administratorMapper;
    private final SqlMapper sqlMapper;

    public UserInfo getUserInfo(String user_id) {
        User user = userMapper.selectByPrimaryKey(user_id);
        return new UserInfo(user.getId(), user.getRole(), user.getName(), user.getPhoneNumber(), user.getEmail(), user.getCreateTime());
    }

    public User getUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public Map<String, String> getAccountInfoById(String id) {
        Map<String, String> infos = new HashMap<>();
        String role = userMapper.selectByPrimaryKey(id).getRole();
        switch (role) {
            case "TEA":
                Teacher teacher = teacherMapper.selectByPrimaryKey(id);
                infos.put("email", teacher.getEmail());
                infos.put("phoneNumber", teacher.getPhoneNumber());
                break;
            case "STU":
                Student student = studentMapper.selectByPrimaryKey(id);
                infos.put("email", student.getEmail());
                infos.put("phoneNumber", student.getPhoneNumber());
                break;
            case "ADM":
                Administrator administrator = administratorMapper.selectByPrimaryKey(id);
                infos.put("email", administrator.getEmail());
                infos.put("phoneNumber", administrator.getPhoneNumber());
                break;
        }
        infos.put("length", String.valueOf(infos.size()));
        return infos;
    }

    public Map<String, String> updateAccountInfo(String id, String role, String phoneNumber, String email) {
        Map<String, String> infos = new HashMap<>();
        int length = 0;
        switch (role) {
            case "TEA":
                Teacher teacher = teacherMapper.selectByPrimaryKey(id);
                teacher.setPhoneNumber(phoneNumber);
                teacher.setEmail(email);
                length += teacherMapper.updateByPrimaryKeySelective(teacher);
                break;
            case "STU":
                Student student = studentMapper.selectByPrimaryKey(id);
                student.setPhoneNumber(phoneNumber);
                student.setEmail(email);
                length += studentMapper.updateByPrimaryKeySelective(student);
                break;
            case "ADM":
                Administrator administrator = administratorMapper.selectByPrimaryKey(id);
                administrator.setPhoneNumber(phoneNumber);
                administrator.setEmail(email);
                length += administratorMapper.updateByPrimaryKeySelective(administrator);
                break;
        }
        infos.put("length", String.valueOf(length));
        return infos;
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
