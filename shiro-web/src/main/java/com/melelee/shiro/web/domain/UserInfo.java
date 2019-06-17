package com.melelee.shiro.web.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 *
 * @author mengll
 * @create 2019-04-29 17:17
 **/
@Data
@Entity
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue
    private Integer uid;
    @Column(unique = true)
    //帐号
    private String username;
    //名称（昵称或者真实姓名，不同系统不同定义）
    private String name;
    //密码;
    private String password;
    //加密密码的盐
    private String salt;
    //用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
    private byte state;
    //立即从数据库中进行加载数据;
    // 一个用户具有多个角色
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "uid")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roleList;

    // 省略 get set 方法
}
