package com.example.demo.Controller;

import com.example.demo.Dao.UserDao;
import com.example.demo.Exceptions.CantFindUser;
import com.example.demo.Exceptions.NoAdmin;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    UserDao userDao;

    @RequestMapping(value = "userLogin")
    public String Userlogin(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password,
                            @RequestParam(value = "isAdmin") int isAdmin, HttpServletRequest request) {
        User user = userDao.getUserByUserNameAndPassWord(name, password);
        if (user == null)
            throw new CantFindUser();
        if (isAdmin == 1)
            //  return "redirect:/api/login/AdminLogin?name="+name+"&password="+password;
            return AdminLogin(name, password, request);
        else {
            HttpSession session = request.getSession(true);
            session.setAttribute("User", user);
        }
        //如果那么写就不能post，只能get
        //return "UserOptions.html";
        return "redirect:/UserOptions.html";
    }

    @RequestMapping(value = "AdminLogin")
    public String AdminLogin(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password,
                             HttpServletRequest request) {
        User user = userDao.getUserByUserNameAndPassWord(name, password);
        if (user == null)
            throw new CantFindUser();
        if (!user.isAdmin())
            throw new NoAdmin();
        else {
            HttpSession session = request.getSession(true);
            session.setAttribute("User", user);
        }
        return "redirect:/AdminOptions.html";
    }

}
