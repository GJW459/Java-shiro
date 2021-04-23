package com.gjw.shirospringboot.dao;

import com.gjw.shirospringboot.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author 郭经伟
 * @Date 2021/4/10
 **/
public interface UserDao {

    
    void save(@Param("user") User user);

    String getPasswordByUserName(@Param("username") String username);

    String getSaltByUserName(@Param("username")String username);

    int countUserName(@Param("username")String username);
}
