package org.t01.gdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.t01.gdp.GrantedAuthority.MyGrantedAuthority;
import org.t01.gdp.domain.User;
import org.t01.gdp.mapper.UserMapper;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userMapper.selectByPrimaryKey(id);

        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        MyGrantedAuthority myGrantedAuthority = new MyGrantedAuthority(user.getRole());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(myGrantedAuthority);

        return new org.springframework.security.core.userdetails.User(user.getId(),user.getPassword(),authorities);
    }
}
