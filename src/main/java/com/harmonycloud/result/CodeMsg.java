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
    public static CodeMsg PATIENT_NOT_EXIST = new CodeMsg("Patient is not exist");
    public static CodeMsg DATA_QUERY_ERROR = new CodeMsg("Data query error");
    public static CodeMsg SERVICE_ERROR = new CodeMsg("Service error");
    public static CodeMsg PARAMETER_ERROR = new CodeMsg("Parameter error");
    public static CodeMsg DUPLICATED_BOOKING = new CodeMsg("Duplicated booking");
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
