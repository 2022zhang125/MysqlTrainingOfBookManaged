package com.believesun.mysqltrain.mapper;

import com.believesun.mysqltrain.pojo.User;

public interface LoginMapper {
    // 根据username查询用户信息
    User selectUserByUsername(String username);

//    Boolean isExistsByPassword(String password);
}
