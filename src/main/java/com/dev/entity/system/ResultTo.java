package com.dev.entity.system;


/** 
 * @ClassName: ResultTo
 * @description: 
 * @author: wen.dai
 * @Date: 2019年10月23日 下午2:52:34
 */ 

public class ResultTo<T> {
    private int code = 200;
    private boolean success = true;
    private String msg;
    private T result;

    public ResultTo()  {}

    public ResultTo(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void authException(String msg) {
        this.code = 403;
        this.msg = msg;
        this.success = false;
        this.result=null;
    }

    public void commonException(String msg) {
        this.code = 400;
        this.msg = msg;
        this.success = false;
        this.result=null;
    }

    public void serverException(String msg) {
        this.code = 500;
        this.msg = msg;
        this.success = false;
        this.result=null;
    }
}
