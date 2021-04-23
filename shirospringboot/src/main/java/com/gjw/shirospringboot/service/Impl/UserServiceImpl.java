package com.gjw.shirospringboot.service.Impl;

import com.gjw.shirospringboot.dao.UserDao;
import com.gjw.shirospringboot.entity.User;
import com.gjw.shirospringboot.service.UserService;
import com.gjw.shirospringboot.util.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 郭经伟
 * @Date 2021/4/10
 **/
@Service("userService")
public class UserServiceImpl implements UserService {


    @Resource
    private UserDao userDao;

    @Transactional
    @Override
    public void register(User user) {

        //进行注册
        String salt = SaltUtils.getSalt(8);
        user.setSalt(salt);
        Md5Hash md5Hash=new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        userDao.save(user);
    }
}
