package org.t01.gdp.security.detailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.t01.gdp.GrantedAuthority.MyGrantedAuthority;
import org.t01.gdp.domain.*;
import org.t01.gdp.mapper.AdministratorMapper;
import org.t01.gdp.service.MyLogService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AdministratorDetailService implements UserDetailsService {
    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public UserDetails loadUserByUsername(String admin_id) throws UsernameNotFoundException {
        AdministratorExample administratorExample = new AdministratorExample();
        administratorExample.createCriteria().andAdminIdEqualTo(admin_id);
        List<Administrator> administrators = administratorMapper.selectByExample(administratorExample);
//        System.out.println("TeacherDetailService");

        if(administrators.isEmpty()){
            throw new UsernameNotFoundException("用户名不存在");
        }

        Administrator administrator = administrators.get(0);

        MyGrantedAuthority myGrantedAuthority = new MyGrantedAuthority("ADM");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(myGrantedAuthority);

        return new org.springframework.security.core.userdetails.User(administrator.getAdminId(),administrator.getPassword(),authorities);
    }
}