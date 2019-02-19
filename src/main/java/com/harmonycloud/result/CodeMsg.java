package com.harmonycloud.result;


public class CodeMsg {
    private String msg;

    public CodeMsg(String msg) {
        this.msg = msg;
    }

    /**
     * appointment service
     */
    public static CodeMsg Fail = new CodeMsg("appointment_fail");
//    public static CodeMsg PASSWORD_ERROR = new CodeMsg("password wrong");
//    public static CodeMsg LOGIN_FAIL = new CodeMsg("login failed");

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "msg=" + msg;
    }
}
