package org.gjw;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author 郭经伟
 * @Date 2021/4/10
 **/
public class TestShiroMD5 {

    public static void main(String[] args) {

        // md5的hash散列
//        Md5Hash md5Hash=new Md5Hash();
//        // 对123加密 默认散列1次
//        md5Hash.setBytes("123".getBytes());
//        // 转为16进制
//        String s = md5Hash.toHex();
//        System.out.println(s);
        //使用MD5
        Md5Hash md5Hash = new Md5Hash("123");
        System.out.println(md5Hash.toHex());
        //使用MD5 + salt
        Md5Hash md5Hash1 = new Md5Hash("123", "abcde");
        System.out.println(md5Hash1.toHex());
        //使用MD5+salt+hash散列
        Md5Hash md5Hash2 = new Md5Hash("123", "abcde", 1024);
        System.out.println(md5Hash2.toHex());


    }
}
