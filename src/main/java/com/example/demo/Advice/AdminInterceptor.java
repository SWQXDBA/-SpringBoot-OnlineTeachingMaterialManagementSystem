package com.example.demo.Advice;

import com.example.demo.Dao.UserDao;
import com.example.demo.Exceptions.NoAdmin;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession(false).getAttribute("User");
        User Admin = (User) userDao.getUserByUserId(user.getUserId());
        if (!Admin.isAdmin()) {
            throw new NoAdmin();
        }
        return true;
    }
}
