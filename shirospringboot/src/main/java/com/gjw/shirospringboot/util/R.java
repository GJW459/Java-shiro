package com.gjw.shirospringboot.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 郭经伟
 * @Date 2021/4/10
 **/
@Data
@AllArgsConstructor
public class R {

    /**
     * 是否返回成功
     */
    private Boolean success;
    /**
     * 返回的状态码
     */
    private Integer code;
    /**
     * 返回的消息
     */
    private String message;
    /**
     * 返回的数据
     */
    private Map<String,Object> data=new HashMap<String, Object>();

    // 构造器方法私有
    private R(){

    }

    /**
     * 成功静态方法
     * @return
     */
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static R error(){
        R r=new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }
    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }
    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

}
