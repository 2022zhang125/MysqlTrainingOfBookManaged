package com.believesun.mysqltrain.service;

import com.believesun.mysqltrain.exceptions.UserNotFoundException;
import com.believesun.mysqltrain.pojo.User;

public interface LoginService {
    User isUsername(String username) throws UserNotFoundException;
}
