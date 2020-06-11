package org.t01.gdp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.t01.gdp.domain.User;
import org.t01.gdp.mapper.UserMapper;

import java.util.List;

@SpringBootTest
public class Usertest {
    @Autowired
    UserMapper userMapper;
    @Test
    void test() {
        List<User> users=userMapper.selectByExample(null);
        for(User user:users){
            System.out.println(user.getId());
        }
    }
}
