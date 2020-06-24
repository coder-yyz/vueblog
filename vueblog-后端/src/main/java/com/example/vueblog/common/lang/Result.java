package com.example.vueblog.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    //返回数据封装
    private int code;  //返回状态码
    private String msg;  //返回消息
    private Object data; //返回数据对象
    public static Result success(int code ,String msg , Object data){
        Result result = new Result();
        result.code = code;
        result.msg = msg;
        result.data = data;
        return result;
    }
    public static Result success(String msg , Object data){
        Result result = new Result();
        result.code = 200;
        result.msg = msg;
        result.data = data;
        return result;
    }
    public static Result success(Object data){
        Result result = new Result();
        result.code = 200;
        result.msg = "操作成功";
        result.data = data;
        return result;
    }
    public static Result fail(int code ,String msg , Object data){
        Result result = new Result();
        result.code = code;
        result.msg = msg;
        result.data = data;
        return result;
    }
    public static Result fail(String msg , Object data){
        Result result = new Result();
        result.code = 404;
        result.msg = msg;
        result.data = data;
        return result;
    }
    public static Result fail(String msg){
        Result result = new Result();
        result.code = 404;
        result.msg = msg;
        result.data = null;
        return result;
    }

}
