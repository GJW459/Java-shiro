package com.gjw.shirospringboot.controller;

import com.gjw.shirospringboot.entity.User;
import com.gjw.shirospringboot.service.UserService;
import com.gjw.shirospringboot.util.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 郭经伟
 * @Date 2021/4/10
 **/
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public R login(@RequestBody User user){

        //获取主体
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            return R.error().message("密码错误");
        }catch (UnknownAccountException e){
            e.printStackTrace();
            return R.error().message("用户名不存在");
        }
        return R.ok().message("登录成功!!!");
    }

    @PostMapping("/register")
    public R register(@RequestBody User user){

        try {
            userService.register(user);
            return R.ok().message("注册成功!!!");
        }catch (Exception e){
            return  R.error().message("注册失败");
        }
    }
}
