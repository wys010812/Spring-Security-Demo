package cn.codepure.springsecuritydemo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

//    @RequestMapping("login")
//    public String login() {
//        System.out.println("调用了登录方法");
//        return "redirect:main.html";
//    }

    // 严格区分大小写 前缀必须为ROLE_xxx
    // @Secured("ROLE_abc1")
    // PreAuthorize的表达式允许ROLE_开头，也可以不ROLE_开头，配置类不允许ROLE_，不过都是大小写一致
    @PreAuthorize("hasRole('abc')")
    @RequestMapping("toMain")
    public String toMain() {
        return "redirect:main.html";
    }

    @RequestMapping("toError")
    public String toError() {
        return "redirect:error.html";
    }

    @RequestMapping("demo")
    public String demo() {
        return "demo";
    }

//    @GetMapping("demo")
//    @ResponseBody
//    public String demo() {
//        return "demo";
//    }
}
