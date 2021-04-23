package com.gjw.shirospringboot.shiro;

import com.gjw.shirospringboot.dao.UserDao;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author 郭经伟
 * @Date 2021/4/10
 **/
public class UserRealm extends AuthorizingRealm {


    @Resource
    private UserDao userDao;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        if (primaryPrincipal.equals("gjw")){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRoles(Arrays.asList("admin","user","super"));
            simpleAuthorizationInfo.addStringPermissions(Arrays.asList("user:create:*","user:find:*","user:update:*"));
            return simpleAuthorizationInfo;
        }
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        int count = userDao.countUserName(username);
        if(count==1){
            //有当前用户名
            //查询db中的password
            String password = userDao.getPasswordByUserName(username);
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
