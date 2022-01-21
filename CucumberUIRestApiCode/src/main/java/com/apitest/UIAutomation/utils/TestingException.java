package com.apitest.UIAutomation.utils;

public class TestingException extends Exception {

    private static final long serialVersionUId = 1L;
    private TestingErrorCode errorCode;
    public TestingException(){}

    public TestingException(TestingErrorCode errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public TestingException(String message){
        super(message);
    }
    public TestingException(Throwable cause){
        super(cause);
    }
    public TestingException(String message, Throwable cause){
        super(message, cause);
    }
    public TestingErrorCode getErrorCode(){
        return this.errorCode;
    }
}
