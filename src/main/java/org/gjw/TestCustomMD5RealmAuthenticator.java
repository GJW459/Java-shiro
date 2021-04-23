package org.gjw;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.gjw.credentialMatcher.CustomCredentialMatcher;
import org.gjw.realm.CustomerMD5Realm;

import java.util.Arrays;

/**
 * @author 郭经伟
 * @Date 2021/4/10
 **/
public class TestCustomMD5RealmAuthenticator {

    public static void main(String[] args) {

        // 使用shiro的权限认证功能
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        CustomerMD5Realm customerMD5Realm = new CustomerMD5Realm();
        customerMD5Realm.setCredentialsMatcher(new CustomCredentialMatcher());
        defaultSecurityManager.setRealm(customerMD5Realm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();//获取主体
        // 要使用MD5+Salt+hash散列要使用自定义的凭证匹配器
        // 凭证判断是否相同主要是通过 AuthenticatingRealm assertCredentialsMatch判断凭证是否匹配
        //封装token
        UsernamePasswordToken token = new UsernamePasswordToken("xiaoguo", "123");
        try {
            subject.login(token);
            System.out.println("登录成功!!!");
        }catch (Exception e){
            e.printStackTrace();
        }
        //进行授权
        if(subject.isAuthenticated()){
            // 单角色控制
            System.out.println(subject.hasRole("admin"));
            // 多角色控制
            System.out.println(subject.hasAllRoles(Arrays.asList("admin","user","super")));
            // 是否有下列的某一角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin", "user"));
            for(boolean flag:booleans){
                System.out.println(flag);
            }
            System.out.println(subject.isPermitted("user:create:001"));
        }
    }


}
