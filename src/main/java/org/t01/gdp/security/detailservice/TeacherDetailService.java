package org.t01.gdp.security.detailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.t01.gdp.GrantedAuthority.MyGrantedAuthority;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.TeacherMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class TeacherDetailService implements UserDetailsService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public UserDetails loadUserByUsername(String teacher_id) throws UsernameNotFoundException {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andTeacherIdEqualTo(teacher_id);
        List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
//        System.out.println("TeacherDetailService");

        if(teachers.isEmpty()){
            throw new UsernameNotFoundException("用户名不存在");
        }

        Teacher teacher = teachers.get(0);

        MyGrantedAuthority myGrantedAuthority = new MyGrantedAuthority("TEA");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(myGrantedAuthority);

        return new org.springframework.security.core.userdetails.User(teacher.getTeacherId(),teacher.getPassword(),authorities);
    }
}