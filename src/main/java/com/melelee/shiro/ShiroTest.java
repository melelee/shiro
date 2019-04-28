package com.melelee.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;


/**
 * shiro测试第一例
 *
 * @author mengll
 * @create 2019-04-28 19:08
 **/
@Slf4j
public class ShiroTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void before() {
        simpleAccountRealm.addAccount("user", "password", "admin", "user");
    }

    @Test
    public void login() {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);


        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("user", "password");
        subject.login(usernamePasswordToken);
        log.info("isLogin {}", subject.isAuthenticated());

        subject.checkRole("admin");
        subject.checkRoles("admin","user");

        subject.logout();
        log.info("isLogin {}", subject.isAuthenticated());
    }
}
