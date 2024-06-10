package com.believesun.mysqltrain.web;

import com.believesun.mysqltrain.exceptions.UserNotFoundException;
import com.believesun.mysqltrain.exceptions.UserPasswordErrorException;
import com.believesun.mysqltrain.pojo.User;
import com.believesun.mysqltrain.service.impl.LoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        User user = null;
        try {
            user = loginService.isUsername(username);
            // 判断密码是否正确
            Boolean flag = loginService.isTrueOfUserObj(user,password);
            // 账号密码都正确
            response.sendRedirect("web/main/mainPage.jsp");
        } catch (UserNotFoundException e) {
            // 账号错误
            // 直接让其重定向到UserNotFoundPage
            response.sendRedirect("web/error/UserNotFoundPage.jsp");
        } catch (UserPasswordErrorException e) {
            // 密码错误
            response.sendRedirect("web/error/UserPasswordErrorPage.jsp");
        }
    }
}
