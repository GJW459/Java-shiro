package org.gjw;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.gjw.realm.CustomerRealm;

/**
 * @author 郭经伟
 * @Date 2021/4/9
 **/
public class TestCustomerRealmAuthenticator {

    public static void main(String[] args) {

        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(new CustomerRealm());
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        //创建token
        UsernamePasswordToken token = new UsernamePasswordToken("gjw", "123");

        try {
            subject.login(token);
            System.out.println("登录成功!!!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
