package cn.codepure.springsecuritydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService实现类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder pw;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("执行了loadUserByUsername方法");
        // 1. 查询数据库用户名是否存在，如果不存在抛出异常UsernameNotFoundException
        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 2. 把查询出的密码（注册时已经加密过）进行解析，或者直接把密码放入构造方法
        String password = pw.encode("123456");
        return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal,ROLE_abc"));
    }
}
