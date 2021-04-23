package com.gjw.shirospringboot.config;

import com.gjw.shirospringboot.credentialMatcher.CustomMD5CredentialMatcher;
import com.gjw.shirospringboot.shiro.UserRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 郭经伟
 * @Date 2021/4/10
 * springboot整合shiro配置
 **/
@Configuration
public class ShiroConfig {

    // 1.创建ShiroFilter 拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //配置系统受限资源
        //配置系统公共资源
        Map<String,String> map=new HashMap<>();
        map.put("/login","anon");//anon 设置这个资源是公共资源
        map.put("/register","anon");
        map.put("/**","authc");//authc 请求这个资源需要认证和授权
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/home");
        return shiroFilterFactoryBean;
    }
    // 2.创建安全管理器

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm") Realm realm){

        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 安全管理器设置realm
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }
    // 3.创建连接数据库的realm
    @Bean("myRealm")
    public Realm getRealm(CredentialsMatcher credentialsMatcher){
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher);
        return userRealm;
    }

    @Bean("credentialsMatcher")
    public CredentialsMatcher credentialsMatcher(){
        CustomMD5CredentialMatcher credentialMatcher = new CustomMD5CredentialMatcher();
        return credentialMatcher;
    }
}
