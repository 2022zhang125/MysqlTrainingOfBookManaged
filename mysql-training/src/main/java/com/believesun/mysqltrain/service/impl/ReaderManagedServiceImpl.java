package com.believesun.mysqltrain.service.impl;

import com.believesun.mysqltrain.exceptions.ReaderAddErrorException;
import com.believesun.mysqltrain.mapper.ReaderManagedMapper;
import com.believesun.mysqltrain.mapper.impl.ReaderManagedMapperImpl;
import com.believesun.mysqltrain.pojo.Reader;
import com.believesun.mysqltrain.service.ReaderManagedService;
import com.believesun.mysqltrain.utils.SqlSessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

public class ReaderManagedServiceImpl implements ReaderManagedService {
    private static final ReaderManagedMapper readerManaged = new ReaderManagedMapperImpl();

    /**
     * 添加用户方法
     * @param request 请求
     * @param response 响应
     * @return 是否添加成功
     * @throws ReaderAddErrorException 添加失败异常
     */
    @Override
    public Boolean addReader(HttpServletRequest request, HttpServletResponse response) throws ReaderAddErrorException {
        // 开启事务
        SqlSession sqlSession = SqlSessionUtil.openSession();

        // 创建对象
        Reader reader = new Reader();
        // 获取时间戳
        long timestamp = Long.parseLong(request.getParameter("timestamp"));
        int x = (int)timestamp;
        Integer id = (x / 100);
        // 生成读者ID 读者ID = 时间戳
        reader.setId(id);
        reader.setrTypeId(Integer.parseInt(request.getParameter("reader_type")));
        reader.setNo(request.getParameter("reader_id"));
        reader.setName(request.getParameter("reader_name"));
        reader.setPassword(request.getParameter("reader_password"));
        reader.setSex(request.getParameter("gender"));
        reader.setDept(request.getParameter("department"));

        // 读者类别
        String readerType = readerManaged.selectTypeByTypeId(Integer.parseInt(request.getParameter("reader_type")));
        reader.setType(readerType);
        // 目前在借数量
        // 目前在借数量 让表中数据变为0，因为是新增，所以肯定是0。
        reader.setNowBorrowNum(0);
        // 历史借出数量 如果是第一次的话应该是0。
        reader.setHistoryBorrowNum(0);
        // 更新数据库内数据
        int count = readerManaged.insertReader(reader);
        if (count < 0) {
            throw new ReaderAddErrorException("用户新增错误");
        }
        // 事务提交
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
        return true;
    }

    @Override
    public Boolean editReader(HttpServletRequest request, HttpServletResponse response) {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        // 获取读者编号
        String readerNo = request.getParameter("readerNo");
        System.out.println(readerNo);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
        return true;
    }
}
