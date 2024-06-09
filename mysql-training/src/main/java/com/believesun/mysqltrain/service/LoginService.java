package com.believesun.mysqltrain.service;

import com.believesun.mysqltrain.exceptions.UserNotFoundException;
import com.believesun.mysqltrain.exceptions.UserPasswordErrorException;
import com.believesun.mysqltrain.pojo.User;

public interface LoginService {
    // 判断用户是否存在
    User isUsername(String username) throws UserNotFoundException;
    // 判断用户密码是否正确
    /*Boolean isPassword(String password) throws UserPasswordErrorException;*/

    Boolean isTrueOfUserObj(User user,String password) throws UserPasswordErrorException;
}
