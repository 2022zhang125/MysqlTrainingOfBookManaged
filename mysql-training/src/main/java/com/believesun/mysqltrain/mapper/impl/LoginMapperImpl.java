package com.believesun.mysqltrain.mapper.impl;

import com.believesun.mysqltrain.mapper.LoginMapper;
import com.believesun.mysqltrain.pojo.User;
import com.believesun.mysqltrain.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class LoginMapperImpl implements LoginMapper {
    private static final SqlSession sqlSession = SqlSessionUtil.openSession();
    private static final LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
    @Override
    public User selectUserByUsername(String username) {
        User user = mapper.selectUserByUsername(username);
        return user;
    }

}
