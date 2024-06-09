package com.hbsfdxwlxy.competition.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
/**
 * MyBatis工具类，用于控制SqlSession的获取以及控制事务
 * @author BelieveSun
 * @version 1.0
 * @since 1.0
 */
public class SqlSessionUtil {
    private void sqlSessionUtils(){}

    // 该对象的作用域是一个数据库一个对象，而且只要创建一次
    private static SqlSessionFactory sqlSessionFactory;
    static{
        // 在类加载的时候，创建对象
        // 这里的SqlSessionFactoryBuilder对象，只需要将sqlSessionFactory创建出来就可以释放掉了
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ThreadLocal<SqlSession> local = new ThreadLocal<>();

    /**
     * 开启会话
     * @return 会话对象
     */
    public static SqlSession openSession(){
        // 使用ThreadLocal集合统一sqlSession对象
        SqlSession sqlSession = local.get();
        if (sqlSession == null) {
            // 创建一个sqlSession对象，并存入数组中
            sqlSession = sqlSessionFactory.openSession();
            local.set(sqlSession);
        }
        // 不为空，则取出
        return sqlSession;
    }

    /**
     * 关闭sqlSession对象
     * @param sqlSession 待关闭sqlSession对象
     */
    public static void close(SqlSession sqlSession){
        if (sqlSession != null) {
            sqlSession.close();
            // 在移除的同上要消去ThreadLocal中的映射，防止Tomcat多线程导致的value关闭
            local.remove();
        }
    }
}
