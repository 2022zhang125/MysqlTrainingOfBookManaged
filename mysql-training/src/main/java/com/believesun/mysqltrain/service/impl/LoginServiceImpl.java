package com.believesun.mysqltrain.service.impl;

import com.believesun.mysqltrain.exceptions.UserNotFoundException;
import com.believesun.mysqltrain.exceptions.UserPasswordErrorException;
import com.believesun.mysqltrain.mapper.impl.LoginMapperImpl;
import com.believesun.mysqltrain.pojo.User;
import com.believesun.mysqltrain.service.LoginService;
import com.believesun.mysqltrain.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class LoginServiceImpl implements LoginService {
    private static final LoginMapperImpl loginMapper = new LoginMapperImpl();
    @Override
    public User isUsername(String username) throws UserNotFoundException {
        // 开启事务
        SqlSession sqlSession = SqlSessionUtil.openSession();

        // 依据username去数据库查询该用户
        User user = loginMapper.selectUserByUsername(username);
        if (user != null) {
            // 说明用户存在
            return user;
        }
        // 关闭事务
        SqlSessionUtil.close(sqlSession);
        throw new UserNotFoundException("用户不存在");
    }

    /**
     * 依据这个user对象来判断密码是否正确
     * @param user user对象
     * @return
     */
    @Override
    public Boolean isTrueOfUserObj(User user,String password) throws UserPasswordErrorException {
        if (!user.getPassword().equals(password)) {
            // 密码错误
            throw new UserPasswordErrorException("密码错误");
        }
        return true;
    }
}
