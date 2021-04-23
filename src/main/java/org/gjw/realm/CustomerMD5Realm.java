package org.gjw.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;

/**
 * @author 郭经伟
 * @Date 2021/4/10
 * 自定义MD5的Realm
 **/
public class CustomerMD5Realm extends AuthorizingRealm {

    /**
     * 获取认证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获得身份信息
        String principal = ((String) token.getPrincipal());
        if(principal.equals("xiaoguo")){
            return new SimpleAuthenticationInfo(principal,"b0fc6107935e5cb7b711b22c0988e24b",this.getName());
        }
        return null;
    }

    /**
     * 获取授权信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // 认证的时候也会进入这里这里主要是进行权限的初始化
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        System.out.println("主要信息:"+primaryPrincipal);
        // 进行权限角色分配
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addRoles(Arrays.asList("admin","user","super"));
        simpleAuthorizationInfo.addStringPermission("user:*:001");
        return simpleAuthorizationInfo;
    }
}
