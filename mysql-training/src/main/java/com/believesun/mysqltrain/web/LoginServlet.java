package com.believesun.mysqltrain.web;

import com.believesun.mysqltrain.exceptions.UserNotFoundException;
import com.believesun.mysqltrain.pojo.User;
import com.believesun.mysqltrain.service.impl.LoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.swing.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final LoginServiceImpl loginService = new LoginServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 主要是验证其账户是否存在，其密码是否正确
        // 获取账号密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 验证账户是否存在
        /*Boolean flag = null;*/
        User user = new User();
        try {
            user = loginService.isUsername(username);
            // 判断密码是否正确

        } catch (UserNotFoundException e) {
            /*throw new RuntimeException(e);*/
            // 直接让其重定向到UserNotFoundPage
            response.sendRedirect("web/error/UserNotFoundPage.jsp");
        }
    }
}
