package com.example.demo.Controller;

import com.example.demo.Dao.UserDao;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping
public class LoginController {
    @Autowired
    UserDao userDao;

    @RequestMapping(value = "userLogin", method = GET)
    public String Userlogin(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password,
                            HttpServletRequest request) {
        User user = userDao.getUserByUserNameAndPassWord(name, password);
        if (user == null)
            return "redirect:/error/cantFind";
        else {
            HttpSession session = request.getSession(true);
            session.setAttribute("User", user);
        }
        return "UserOptions.html";
    }

    @RequestMapping(value = "AdminLogin", method = GET)
    public String AdminLogin(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password,
                             HttpServletRequest request) {
        User user = userDao.getUserByUserNameAndPassWord(name, password);
        if (user == null)
            return "redirect:/error/cantFind";
        else {
            HttpSession session = request.getSession(true);
            session.setAttribute("User", user);
        }
        return "AdminOptions.html";
    }

}
