package org.t01.gdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t01.gdp.domain.User;
import org.t01.gdp.domain.UserInfo;
import org.t01.gdp.mapper.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public UserInfo getUserInfo(String user_id) {
        User user = userMapper.selectByPrimaryKey(user_id);
        return new UserInfo(user.getId(), user.getRole(), user.getName(), user.getPhoneNumber(), user.getEmail(), user.getCreateTime());
    }

    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    public int updateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    public int deleteUserById(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public List<User> getUserByRole(String role) {
        return userMapper.selectByRole(role);
    }
}
