package com.harmonycloud.result;


public class CodeMsg {
    private String msg;

    public CodeMsg() {
    }

    public CodeMsg(String msg) {
        this.msg = msg;
    }

    /**
     * appointment service
     */
    public static CodeMsg PATIENT_NOT_EXIST = new CodeMsg("patient is not exist");
    public static CodeMsg DATA_QUERY_ERROR = new CodeMsg("data query error");
//    public static CodeMsg LOGIN_FAIL = new CodeMsg("login failed");

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
