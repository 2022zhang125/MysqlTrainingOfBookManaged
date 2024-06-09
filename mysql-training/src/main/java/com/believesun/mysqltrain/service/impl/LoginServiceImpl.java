package com.believesun.mysqltrain.service.impl;

import com.believesun.mysqltrain.exceptions.UserNotFoundException;
import com.believesun.mysqltrain.mapper.impl.LoginMapperImpl;
import com.believesun.mysqltrain.pojo.User;
import com.believesun.mysqltrain.service.LoginService;

public class LoginServiceImpl implements LoginService {
    private static final LoginMapperImpl loginMapper = new LoginMapperImpl();
    @Override
    public User isUsername(String username) throws UserNotFoundException {
        // 依据username去数据库查询该用户
        User user = loginMapper.selectUserByUsername(username);
        if (user != null) {
            // 说明用户存在
            return user;
        }
        throw new UserNotFoundException("用户不存在");
    }
}
