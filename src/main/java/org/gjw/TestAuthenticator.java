package org.gjw;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * @author 郭经伟
 * @Date 2021/4/9
 **/
public class TestAuthenticator {

    public static void main(String[] args) {

        //1.创建安全管理器
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        //2.Realm提供数据库数据 给安全管理器设置realm
        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));
        //3.身份信息和凭证组成token
        // SecurityUtils 全局安全工具类 设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        //4.关键对象 subject 主体
        Subject subject = SecurityUtils.getSubject();
        //5.创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("xiaoguo", "123");
        try {
            // 用户认证
            System.out.println("认证状态"+subject.isAuthenticated());
            subject.login(token);
            // 认证状态
            System.out.println("认证状态"+subject.isAuthenticated());
        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("认证失败：用户名不存在");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("认证失败：密码错误");
        }
    }
}
