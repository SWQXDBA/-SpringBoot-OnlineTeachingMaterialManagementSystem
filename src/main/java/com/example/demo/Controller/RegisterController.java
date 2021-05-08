package com.example.demo.Controller;

import com.example.demo.Dao.UserDao;
import com.example.demo.Exceptions.RegistedException;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;

@Controller
@RequestMapping("register")
public class RegisterController {
    @Autowired
    UserDao userDao;

    @Transactional
    @GetMapping("registerUser")
    public String registerUser(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {

        if (userDao.countUserByUserName(name) != 0) {
            throw new RegistedException();
        }
        User user = new User();
        user.setUserName(name);
        user.setAdmin(false);
        user.setPassWord(password);
        userDao.save(user);
        return "redirect:/login.html";
    }

    @ResponseBody
    @Transactional
    @GetMapping("registerAdmin")
    public String registerAdmin(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password
            , @RequestParam(value = "identity") String identity) {
        //重定向到注册过的界面
        if (userDao.countUserByUserName(name) != 0) {
            throw new RegistedException();
        }
        if (!identity.equals("666"))
            return "校验码错误！";
        User user = new User();
        user.setUserName(name);
        user.setAdmin(true);
        user.setPassWord(password);
        userDao.save(user);
        return "管理员注册成功！";
    }
}
