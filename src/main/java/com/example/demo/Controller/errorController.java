package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("error")
public class errorController {
    @ResponseBody
    @RequestMapping(value = "registed", method = RequestMethod.GET)
    public String registed(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
        return "该用户名已注册!!";
    }

    @ResponseBody
    @RequestMapping(value = "cantFind", method = RequestMethod.GET)
    public String cantFind() {
        return "用户名或密码错误!!";
    }
}
