package com.harmonycloud.enums;


public enum ErrorMsgEnum {

    SERVICE_ERROR("Internal service error"),
    BOOK_ERROR("Book error"),
    DUPLICATED_BOOKING("Duplicated booking"),
    FUll_BOOKING("Full booking"),
    ILLEGAL("Illegal modification"),
    FORMAT_ERROR("Unable to parse the proxy port number"),
    AUTHENTICATION_ERROR("Could not set user authentication in security context"),
    PARAMETER_ERROR("Parameter error");

    private String message;

    ErrorMsgEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
