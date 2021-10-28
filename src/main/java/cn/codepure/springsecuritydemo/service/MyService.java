package cn.codepure.springsecuritydemo.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface MyService {
    boolean hasPermission(HttpServletRequest httpServletRequest, Authentication authentication);
}
