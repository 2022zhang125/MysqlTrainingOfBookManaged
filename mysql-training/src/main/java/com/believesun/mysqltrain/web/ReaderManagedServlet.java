package com.believesun.mysqltrain.web;

import ch.qos.logback.core.LogbackException;
import com.believesun.mysqltrain.exceptions.ReaderAddErrorException;
import com.believesun.mysqltrain.service.impl.ReaderManagedServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/reader/*")
public class ReaderManagedServlet extends HttpServlet {
    private static final ReaderManagedServiceImpl readerManaged = new ReaderManagedServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取进行的操作
        String pathValue = request.getPathInfo();
        switch (pathValue){
            case "/add":
                try {
                    readerManaged.addReader(request,response);
                } catch (ReaderAddErrorException e) {
                    // 用户新增错误,重定向到用户新增错误页面
                    response.sendRedirect(request.getContextPath()+"/web/error/ReaderAddErrorPage.jsp");
                }
                // 插入成功
                // 跳转到successAdd界面
                response.sendRedirect(request.getContextPath()+"/web/main/usermanaged/success/successAdd.jsp");
                break;

            case "/edit":
                // 先查找
                readerManaged.editReader(request,response);
                // 再更新
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + pathValue);
        }
    }
}
