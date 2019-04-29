package com.melelee.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * IniReaml测试
 *
 * @author mengll
 * @create 2019-04-28 19:40
 **/
@Slf4j
public class JdbcRealmTest {

    JdbcRealm jdbcRealm = new JdbcRealm();

    {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://45.78.57.75:3306/shiro");
        druidDataSource.setUsername("");
        druidDataSource.setPassword("");
        jdbcRealm.setDataSource(druidDataSource);

        jdbcRealm.setAuthenticationQuery("select password from user where user_name = ?");
    }


    @Test
    public void login() {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);


        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("user", "password");
        subject.login(usernamePasswordToken);
        log.info("isLogin {}", subject.isAuthenticated());

        subject.checkRole("admin");
        subject.checkRoles("admin","user");
        subject.checkPermission("user:update");

        subject.logout();
        log.info("isLogin {}", subject.isAuthenticated());
    }
}
