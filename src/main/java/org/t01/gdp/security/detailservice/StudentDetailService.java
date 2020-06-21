package org.t01.gdp.security.detailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.t01.gdp.grantedauthority.MyGrantedAuthority;
import org.t01.gdp.domain.Student;
import org.t01.gdp.domain.StudentExample;
import org.t01.gdp.mapper.StudentMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class StudentDetailService implements UserDetailsService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public UserDetails loadUserByUsername(String student_id) throws UsernameNotFoundException {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStudentIdEqualTo(student_id);
        List<Student> students = studentMapper.selectByExample(studentExample);
//        System.out.println("StudentDetailService");

        if(students.isEmpty()){
            throw new UsernameNotFoundException("用户名不存在");
        }

        Student student = students.get(0);

        MyGrantedAuthority myGrantedAuthority = new MyGrantedAuthority("STU");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(myGrantedAuthority);

        return new org.springframework.security.core.userdetails.User(student.getStudentId(),student.getPassword(),authorities);
    }
}