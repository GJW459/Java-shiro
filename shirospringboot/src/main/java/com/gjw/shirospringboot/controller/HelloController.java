package com.gjw.shirospringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 郭经伟
 * @Date 2021/4/10
 **/
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @GetMapping("/home")
    public String home(){
        return "return home";
    }

}
