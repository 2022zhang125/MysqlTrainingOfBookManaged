package com.believesun.mysqltrain.mapper.impl;

import com.believesun.mysqltrain.mapper.LoginMapper;
import com.believesun.mysqltrain.pojo.User;
import com.believesun.mysqltrain.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class LoginMapperImpl implements LoginMapper {

    @Override
    public User selectUserByUsername(String username) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
        User user = mapper.selectUserByUsername(username);
        return user;
    }
}
