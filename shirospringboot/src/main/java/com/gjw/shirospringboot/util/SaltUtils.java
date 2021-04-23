package com.gjw.shirospringboot.util;

import java.util.Random;

/**
 * @author 郭经伟
 * @Date 2021/4/10
 **/
public class SaltUtils {

    /**
     * 生成盐
     * @param n
     * @return
     */
    public static String getSalt(int n){
        char[] chars="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i <n ; i++) {
            char aChar=chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String salt = SaltUtils.getSalt(4);
        System.out.println(salt);
    }
}
