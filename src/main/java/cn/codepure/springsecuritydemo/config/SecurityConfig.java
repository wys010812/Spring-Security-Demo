package cn.codepure.springsecuritydemo.config;

import cn.codepure.springsecuritydemo.handle.MyAuthenticationFailureHandler;
import cn.codepure.springsecuritydemo.handle.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单提交
        http.formLogin()
                // 自定义属性参数名
                .usernameParameter("user")
                .passwordParameter("password")
                // 当发现是/login认为是登录，必须和表单提交地址一致会执行UserDetailsServiceImpl的登录逻辑
                .loginProcessingUrl("/login")
                // 自定义登录页面
                .loginPage("/login.html")
                // 登录成功后跳转页面必须是POST请求
                // .successForwardUrl("/toMain")
                // 登录成功后处理器不能和我的successForwardUrl共存不然会报错
                .successHandler(new MyAuthenticationSuccessHandler("https://www.baidu.com"))
                // 登录失败后跳转页面必须是POST请求
                // .failureForwardUrl("/toError");
                // 登录失败后处理器不能和我的failureForwardUrl共存不然会报错
                .failureHandler(new MyAuthenticationFailureHandler("/error.html"));
        // 授权认证
        http.authorizeRequests()
                // login.html不需要被认证
                .antMatchers("/login.html").permitAll()
                // error.html不需要被认证
                .antMatchers("/error.html").permitAll()
                // 所有请求都必须登录
                .anyRequest().authenticated();

        // 关闭csrf防护
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder getPw() {
        return new BCryptPasswordEncoder();
    }
}
