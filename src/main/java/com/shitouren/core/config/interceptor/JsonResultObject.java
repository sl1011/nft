package com.shitouren.core.config.interceptor;

import com.shitouren.core.config.exception.ExceptionConstant;

/**
 * @Autho： 王涛
 * @DATE： 2018/10/23 16:38
 */
public class JsonResultObject<T> {


    private String code;

    private T data ;

    private String info;


    public JsonResultObject(T data) {
        this.info = ExceptionConstant.处理成功.getMsg();
        this.code = ExceptionConstant.处理成功.getCode();
        this.data = data;
    }

    public JsonResultObject() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
