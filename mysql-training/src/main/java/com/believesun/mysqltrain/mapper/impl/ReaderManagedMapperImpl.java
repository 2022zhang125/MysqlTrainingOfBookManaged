package com.believesun.mysqltrain.mapper.impl;

import com.believesun.mysqltrain.mapper.ReaderManagedMapper;
import com.believesun.mysqltrain.pojo.Reader;
import com.believesun.mysqltrain.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class ReaderManagedMapperImpl implements ReaderManagedMapper {
    @Override
    public String selectTypeByTypeId(Integer typeId) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ReaderManagedMapper mapper = sqlSession.getMapper(ReaderManagedMapper.class);
        String string = mapper.selectTypeByTypeId(typeId);
        return string;
    }

    @Override
    public int insertReader(Reader reader) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ReaderManagedMapper mapper = sqlSession.getMapper(ReaderManagedMapper.class);
        int count = mapper.insertReader(reader);
        return count;
    }

    @Override
    public Reader selectReaderByNo(String no) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ReaderManagedMapper mapper = sqlSession.getMapper(ReaderManagedMapper.class);
        Reader reader = mapper.selectReaderByNo(no);
        return reader;
    }

    @Override
    public Integer selectTypeIdByType(String type) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ReaderManagedMapper mapper = sqlSession.getMapper(ReaderManagedMapper.class);
        Integer integer = mapper.selectTypeIdByType(type);
        return integer;
    }
}
