package com.believesun.mysqltrain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.believesun.mysqltrain.exceptions.ReaderAddErrorException;
import com.believesun.mysqltrain.mapper.ReaderManagedMapper;
import com.believesun.mysqltrain.mapper.impl.ReaderManagedMapperImpl;
import com.believesun.mysqltrain.pojo.Reader;
import com.believesun.mysqltrain.service.ReaderManagedService;
import com.believesun.mysqltrain.utils.SqlSessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;

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
    public Boolean editReader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 获取请求体中的 JSON 数据
        BufferedReader readerJson = request.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = readerJson.readLine()) != null) {
            jsonBuilder.append(line);
        }
        // 解析 JSON 数据
        JSONObject jsonObject = parseObject(jsonBuilder.toString());
        // 获取时间戳
        String timestampOfSubmit = null;
        if (jsonObject != null) {
            timestampOfSubmit = jsonObject.getString("timestamp");
        }
        // 1.获取搜索的时间戳
        String timestampOfSelect = request.getParameter("timestampOfSelect");

        if (timestampOfSelect != null) {
            System.out.println("查询执行了！！！！");
            // 获取读者编号
            String readerNo = request.getParameter("readerNo");
            // 通过这个编号去查找符合对象的reader，直接tmd字符串返回进行
            // 1.查找该对象
            Reader reader = readerManaged.selectReaderByNo(readerNo);

            String readerString = JSON.toJSONString(reader);

            response.setContentType("application/text");
            PrintWriter out = response.getWriter();
            out.print(readerString);
            out.flush();
        } if (timestampOfSubmit != null) {
            // 2.获取前端修改完后的内容，进行数据库更新操作
            int lastIndex = jsonBuilder.lastIndexOf(",\"timestamp");
            // 如果找到了 "timestamp"，则截取从字符串开始到 "timestamp" 出现的位置
            if (lastIndex != -1) {
                String result = jsonBuilder.substring(0, lastIndex) + "}";
                System.out.println(result);
                // 解析 JSON 数据并构建 Reader 对象
                JSONObject json = parseObject(result);
                Reader reader = new Reader();
                reader.setNo(json.getString("modify_reader_no"));
                reader.setId(Integer.valueOf(json.getString("reader_id")));
                reader.setName(json.getString("reader_name"));
                reader.setPassword(json.getString("reader_password"));
                reader.setType(json.getString("reader_type"));
                reader.setDept(json.getString("reader_dept"));
                reader.setSex(json.getString("reader_sex"));
                // 向reader中添加rTypeId属性
                // 根据Type查出TyepId
                reader.setrTypeId(readerManaged.selectTypeIdByType(json.getString("reader_type")));
                System.out.println(reader);
            } else {
                // 如果没有找到 "timestamp"，则输出原始字符串
                System.out.println(jsonBuilder);
            }
        }

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
        return true;
    }
}
