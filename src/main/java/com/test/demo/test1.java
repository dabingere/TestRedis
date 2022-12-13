package com.test.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class test1 {
    public static void main(String[] args) {
        IniSecurityManagerFactory ini = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        SecurityManager securityManager = ini.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zs","123");
        subject.login(token);
        if (subject.isPermitted("user:create")){
            System.out.println("有此权限");
        }else {
            System.out.println("没有该权限！！");
        }
    }
    public static void main2(String[] args) {
        IniSecurityManagerFactory ini = new IniSecurityManagerFactory("classpath:shiro-role.ini");
        SecurityManager securityManager = ini.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zs","123");
        subject.login(token);
        if (subject.hasRole("role4")){
            System.out.println("正常访问");
        }else{
            System.out.println("没有该权限！！");
        }
    }

    public static void main1(String[] args) {
        IniSecurityManagerFactory ini = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = ini.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zs","123");
        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            System.out.println("查无此人");
            throw new RuntimeException(e);
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
            throw new RuntimeException(e);
        }catch (AuthenticationException e) {
            System.out.println("验证失败");
            throw new RuntimeException(e);
        }
//        subject.logout();
    }
}
