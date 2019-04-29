package com.melelee.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * IniReaml测试
 *
 * @author mengll
 * @create 2019-04-28 19:40
 **/
@Slf4j
public class IniRealmTest {

    IniRealm iniRealm = new IniRealm("classpath:user.ini");


    @Test
    public void login() {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);


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
