package com.believesun.mysqltrain.mapper;

import com.believesun.mysqltrain.pojo.User;

public interface LoginServiceMapper {
    // 根据username查询用户信息
    User selectUserByUsername(String username);
}
