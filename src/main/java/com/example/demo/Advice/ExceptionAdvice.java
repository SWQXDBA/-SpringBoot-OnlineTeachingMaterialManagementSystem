package com.example.demo.Advice;

import com.example.demo.Exceptions.CantFindUser;
import com.example.demo.Exceptions.NoAdmin;
import com.example.demo.Exceptions.RegistedException;
import com.example.demo.Exceptions.UnLoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(UnLoginException.class)
    @ResponseBody
    public Object unLogin() {
        return "您未登录！请先登录";
    }

    @ExceptionHandler(CantFindUser.class)
    @ResponseBody
    public Object CantFindUser() {
        return "用户名或密码错误!";
    }

    @ExceptionHandler(RegistedException.class)
    @ResponseBody
    public Object Registed() {
        return "该用户名已注册！";
    }

    @ExceptionHandler(NoAdmin.class)
    @ResponseBody
    public Object Noadmin() {
        return "此操作仅允许管理员用户进行！,请登陆一个管理员账号，或者输入正确的管理员账号密码";
    }
}
