package com.melelee.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * MyRealm测试
 *
 * @author mengll
 * @create 2019-04-29 9:31
 **/
@Slf4j
public class MyRealmTest {

    MyRealm myRealm = new MyRealm();

    {


        //MD5加密一次
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1);
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
    }


    @Test
    public void login() {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(myRealm);


        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("user", "password");
        subject.login(usernamePasswordToken);
        log.info("isLogin {}", subject.isAuthenticated());

//        subject.checkRole("admin");
//        subject.checkRoles("admin","user");
//        subject.checkPermission("user:update");

        subject.logout();
        log.info("isLogin {}", subject.isAuthenticated());
    }
}
