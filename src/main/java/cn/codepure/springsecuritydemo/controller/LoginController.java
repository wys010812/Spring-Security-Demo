package cn.codepure.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

//    @RequestMapping("login")
//    public String login() {
//        System.out.println("调用了登录方法");
//        return "redirect:main.html";
//    }

    @RequestMapping("toMain")
    public String toMain() {
        return "redirect:main.html";
    }

    @RequestMapping("toError")
    public String toError() {
        return "redirect:error.html";
    }
}
