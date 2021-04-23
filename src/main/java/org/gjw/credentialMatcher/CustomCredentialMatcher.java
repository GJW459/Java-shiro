package org.gjw.credentialMatcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.Objects;

/**
 * @author 郭经伟
 * @Date 2021/4/10
 **/
public class CustomCredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //获得用户输入的密码
        Object inPassword = token.getCredentials();
        //获得数据库中的密码
        String dbPassword = (String) info.getCredentials();
        System.out.println("dbPassword:"+dbPassword);
        //进行MD5+Salt+hash
        Md5Hash md5Hash = new Md5Hash(inPassword, "abcde", 1024);
        String md5Password = md5Hash.toHex();
        System.out.println("md5Password:"+md5Password);
        return Objects.equals(md5Password,dbPassword);
    }
}
