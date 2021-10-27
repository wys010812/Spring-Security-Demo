package cn.codepure.springsecuritydemo.handle;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final String url;

    public MyAuthenticationSuccessHandler(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//        User user = (User) authentication.getDetails();
//        System.out.println(user.getUsername());
//        // 输出为null
//        System.out.println(user.getPassword());
//        System.out.println(user.getAuthorities());
        httpServletResponse.sendRedirect(url);
    }
}
