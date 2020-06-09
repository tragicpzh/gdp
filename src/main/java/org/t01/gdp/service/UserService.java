package org.t01.gdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.User;
import org.t01.gdp.domain.UserInfo;
import org.t01.gdp.mapper.UserMapper;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public UserInfo getUserInfo(String user_id){
        User user = userMapper.selectByPrimaryKey(user_id);
        return new UserInfo(user.getId(),user.getRole(),user.getName(),user.getPhoneNumber(),user.getEmail(),user.getCreateTime());
    }
}
