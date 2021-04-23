package org.gjw.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author 郭经伟
 * @Date 2021/4/9
 *
 * 自定义realm实现 将认证和授权的来源转为数据库的实现
 **/
public class CustomerRealm extends AuthorizingRealm {

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //在token中获取用户名
        String principal = (String) token.getPrincipal();
        //身份信息
        System.out.println(principal);
        // 使用jdbc 或者 mybatis查询数据库
        if("gjw".equals(principal)){
            // 参数1 DB中正确的用户名 参数2 DB中正确密码 参数3 提供当前realm的名字
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal,"123",this.getName());
            return  authenticationInfo;
        }
        return null;
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
