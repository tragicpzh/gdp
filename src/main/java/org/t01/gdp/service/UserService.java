package org.t01.gdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final AdministratorMapper administratorMapper;
    private final MajorMapper majorMapper;
    private final CollegeMapper collegeMapper;
    private final UserMapper userMapper;
    private static Set<String> allIds;
    private static Set<String> allPhoneNumbers;
    private static Set<String> allEmails;

    private String getRandomVal() {
        StringBuilder randomVal = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            randomVal.append(random.nextInt(10));
        }
        return randomVal.toString();
    }

    private void initialize() {
        if (allIds == null) {
            allIds = userMapper.getAllIds();
            allPhoneNumbers = userMapper.getAllPhoneNumbers();
            allEmails = userMapper.getAllEmails();
        }
    }

    private String checkNewPhoneNumberAndEmail(String id, String role, String phoneNumber, String email, Map<String, Object> result) {
        if (result.get("phoneNumber") != null && !result.get("phoneNumber").toString().equals(phoneNumber) && allPhoneNumbers.contains(phoneNumber)) {
            return "Phone Number Already Exist";
        }
        if (result.get("email") != null && !result.get("email").toString().equals(email) && allEmails.contains(email)) {
            return "Email Already Exist";
        }
        return "success";
    }

    public String addUser(String name, String phoneNumber, String email, String role, String otherInfo) {
        initialize();

        if (allPhoneNumbers.contains(phoneNumber)) {
            return "Phone Number Already Exist";
        }

        if (allEmails.contains(email)) {
            return "Email Already Exist";
        }

        Calendar calendar = Calendar.getInstance();
        String id = String.valueOf(calendar.get(Calendar.YEAR));
        String randomVal = getRandomVal();
        if (role.equals("TEA")) {
            College college = collegeMapper.selectByPrimaryKey(Long.valueOf(otherInfo));
            if (college == null) {
                return "No Such College";
            }
            id += String.valueOf(college.getId()).length() == 2 ? String.valueOf(college.getId()) : "0" + college.getId();

            while (allIds.contains(id + randomVal)) {
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

        } else if (role.equals("STU")) {
            Major major = majorMapper.selectByPrimaryKey(Long.valueOf(otherInfo));
            if (major == null) {
                return "No Such Major";
            }
            id += String.valueOf(major.getCollegeId()).length() == 2 ? String.valueOf(major.getCollegeId()) : "0" + major.getCollegeId();
            id += String.valueOf(major.getId()).length() == 2 ? String.valueOf(major.getId()) : "0" + major.getId();
            while (allIds.contains(id + randomVal)) {
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
        }
        allIds.add(id);
        allPhoneNumbers.add(phoneNumber);
        allEmails.add(email);
        return "success";
    }

    public String updateUser(String id, String name, String phoneNumber, String email, String role, String otherInfo) {
        initialize();
        Map<String, Object> result = new HashMap<>();
        if (role.equals("TEA")) {
            College college = collegeMapper.selectByPrimaryKey(Long.valueOf(otherInfo));
            if (college == null) {
                return "No Such College";
            }
            result = userMapper.getTeacherByTeacherID(Long.parseLong(id));
            String check = checkNewPhoneNumberAndEmail(id, role, phoneNumber, email, result);
            if (!check.equals("success")) {
                return check;
            }

            if (userMapper.updateTeacherByTeacherID(id, name, phoneNumber, email, otherInfo) != 1) {
                return "Unknown Failure";
            }
        } else if (role.equals("STU")) {
            Major major = majorMapper.selectByPrimaryKey(Long.valueOf(otherInfo));
            if (major == null) {
                return "No Such Major";
            }
            result = userMapper.getStudentByStudentId(Long.parseLong(id));
            String check = checkNewPhoneNumberAndEmail(id, role, phoneNumber, email, result);
            if (!check.equals("success")) {
                return check;
            }
            if (userMapper.updateStudentByStudentId(id, name, phoneNumber, email, otherInfo) != 1) {
                return "Unknown Failure";
            }
        }

        if (allIds != null) {
            if (result.get("phoneNumber") != null && !result.get("phoneNumber").toString().equals(phoneNumber)) {
                allPhoneNumbers.remove(result.get("phoneNumber").toString());
                allPhoneNumbers.add(phoneNumber);
            }

            if (result.get("email") != null && !result.get("email").toString().equals(email)) {
                allEmails.remove(result.get("email").toString());
                allEmails.add(email);
            }
        }
        return "success";
    }

    public Map<String, Object> getUserInfo(String id, String role) {
        switch (role) {
            case "TEA":
                return userMapper.getTeacherByTeacherID(Long.parseLong(id));
            case "STU":
                return userMapper.getStudentByStudentId(Long.parseLong(id));
            case "ADM":
                return userMapper.getAdminByAdminID(Long.parseLong(id));
        }
        return null;
    }

    public String getUserPassword(String id, String role) {
        switch (role) {
            case "TEA":
                return teacherMapper.selectByPrimaryKey(Long.parseLong(id)).getPassword();
            case "STU":
                return studentMapper.selectByPrimaryKey(Long.parseLong(id)).getPassword();
            case "ADM":
                return administratorMapper.selectByPrimaryKey(Long.parseLong(id)).getPassword();
        }
        return null;
    }

    public boolean updateUserPassword(String id, String role, String newPassword) {
        switch (role) {
            case "TEA":
                Teacher teacher = new Teacher();
                teacher.setId(Long.valueOf(id));
                teacher.setPassword(newPassword);
                return teacherMapper.updateByPrimaryKeySelective(teacher) == 1;
            case "STU":
                Student student = new Student();
                student.setId(Long.valueOf(id));
                student.setPassword(newPassword);
                return studentMapper.updateByPrimaryKeySelective(student) == 1;
            case "ADM":
                Administrator administrator = new Administrator();
                administrator.setId(Long.valueOf(id));
                administrator.setPassword(newPassword);
                return administratorMapper.updateByPrimaryKeySelective(administrator) == 1;
        }
        return false;
    }

    public boolean updatePhoneNumber(String id, String role, String phoneNumber) {
        switch (role) {
            case "TEA":
                Teacher teacher = new Teacher();
                teacher.setId(Long.valueOf(id));
                teacher.setPhoneNumber(phoneNumber);
                return teacherMapper.updateByPrimaryKeySelective(teacher) == 1;
            case "STU":
                Student student = new Student();
                student.setId(Long.valueOf(id));
                student.setPhoneNumber(phoneNumber);
                return studentMapper.updateByPrimaryKeySelective(student) == 1;
            case "ADM":
                Administrator administrator = new Administrator();
                administrator.setId(Long.valueOf(id));
                administrator.setPhoneNumber(phoneNumber);
                return administratorMapper.updateByPrimaryKeySelective(administrator) == 1;
        }
        return false;
    }

    public boolean updateEmail(String id, String role, String email) {
        switch (role) {
            case "TEA":
                Teacher teacher = new Teacher();
                teacher.setId(Long.valueOf(id));
                teacher.setEmail(email);
                return teacherMapper.updateByPrimaryKeySelective(teacher) == 1;
            case "STU":
                Student student = new Student();
                student.setId(Long.valueOf(id));
                student.setEmail(email);
                return studentMapper.updateByPrimaryKeySelective(student) == 1;
            case "ADM":
                Administrator administrator = new Administrator();
                administrator.setId(Long.valueOf(id));
                administrator.setEmail(email);
                return administratorMapper.updateByPrimaryKeySelective(administrator) == 1;
        }
        return false;
    }

    public List<Map<String, Object>> getUserByRole(String role) {
        if (role.equals("TEA")) {
            return userMapper.getAllTeachers();
        } else if (role.equals("STU")) {
            return userMapper.getAllStudents();
        }
        return null;
    }

    public boolean deleteUserById(String id, String role) {
        boolean flag = false;
        Map<String, Object> result = new HashMap<>();
        if (role.equals("TEA")) {
            result = userMapper.getTeacherByTeacherID(Long.parseLong(id));
            flag = userMapper.deleteTeacherByTeacherId(Long.parseLong(id)) == 1;
        } else if (role.equals("STU")) {
            result = userMapper.getStudentByStudentId(Long.parseLong(id));
            flag = userMapper.deleteStudentByStudentId(Long.parseLong(id)) == 1;
        }
        if (flag && allIds != null) {
            allIds.remove(id);
            if (result.get("phoneNumber") != null) {
                allPhoneNumbers.remove(result.get("phoneNumber").toString());
            }

            if (result.get("email") != null) {
                allEmails.remove(result.get("email").toString());
            }
        }
        return flag;
    }
}
