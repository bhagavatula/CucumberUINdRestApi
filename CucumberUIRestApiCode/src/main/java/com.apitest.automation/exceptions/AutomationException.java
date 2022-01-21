package com.apitest.automation.exceptions;

public class AutomationException extends RuntimeException {
    private static final long serialVersionUID=1L;
    public  AutomationException(String message, Throwable cause){
        super(message, cause);
    }
}
