package com.gjw.shirospringboot.credentialMatcher;

import com.gjw.shirospringboot.dao.UserDao;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author 郭经伟
 * @Date 2021/4/10
 * 自定义的凭证匹配
 **/
public class CustomMD5CredentialMatcher extends SimpleCredentialsMatcher {

    @Resource
    private UserDao userDao;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //获得用户输入的密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String inPassword = String.valueOf(usernamePasswordToken.getPassword());
        //获得数据库中的密码
        String dbPassword = (String) info.getCredentials();
        //进行MD5+salt+hash
        String salt = userDao.getSaltByUserName(usernamePasswordToken.getUsername());
        Md5Hash md5Hash=new Md5Hash(inPassword,salt,1024);
        String md5Password = md5Hash.toHex();
        return Objects.equals(md5Password,dbPassword);
    }
}
