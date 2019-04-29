package com.melelee.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;

/**
 * 自定义realm
 *
 * @author mengll
 * @create 2019-04-28 20:21
 **/
public class MyRealm extends AuthorizingRealm {
    {
        super.setName("myRealm");
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String user = (String) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(new HashSet<String>());
        simpleAuthorizationInfo.setRoles(new HashSet<String>());

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("user","67a1e09bb1f83f5007dc119c14d663aa",this.getName());
        //加盐
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("salt"));
        return simpleAuthenticationInfo;
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("password","salt");
        System.out.println(md5Hash);
    }
}
