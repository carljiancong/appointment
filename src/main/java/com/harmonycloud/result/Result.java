package com.harmonycloud.result;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;


public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    private Boolean msg;
    private Object data;


    public Result(Boolean msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    public Boolean getMsg() {
        return msg;
    }

    public void setMsg(Boolean msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result() {}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



    public static Result buildError(Object errorData){
        return new Result(false, errorData);
    }

    public static Result buildSuccess(Object data){
        return new Result(true, data);
    }


}
