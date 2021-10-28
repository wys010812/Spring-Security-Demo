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
                .successForwardUrl("/toMain")
                // 登录成功后处理器不能和我的successForwardUrl共存不然会报错
                // .successHandler(new MyAuthenticationSuccessHandler("https://www.baidu.com"))
                // 登录失败后跳转页面必须是POST请求
                .failureForwardUrl("/toError");
                // 登录失败后处理器不能和我的failureForwardUrl共存不然会报错
                // .failureHandler(new MyAuthenticationFailureHandler("/error.html"));
        // 授权认证
        http.authorizeRequests()
                // login.html不需要被认证
                .antMatchers("/login.html").permitAll()
                // error.html不需要被认证
                .antMatchers("/error.html").permitAll()
                // **代表目录下的所有文件 * 代表匹配一个或多个字符（常用)
                .antMatchers("/img/**", "/js/**", "/css/**").permitAll()
                // 过滤所有目录下的png文件
                // .antMatchers("/**/*.png").permitAll()
                // 使用正则表达式放行所有png结尾的图片
                // .regexMatchers(".+[.]png").permitAll()
                // 指定Http请求方法为POST
                // .regexMatchers(HttpMethod.POST, "/demo").permitAll()
                // .mvcMatchers("/demo").servletPath("/xxx").permitAll()
                // mvcMatchers的配置和底下这个是等效的
                // .antMatchers("/xxx/demo").permitAll()
                // 权限
                .antMatchers("/main1.html").hasAnyAuthority("admiN")
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
