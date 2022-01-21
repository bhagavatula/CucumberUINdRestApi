package com.apitest.automation.exceptions;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class AutomationExceptionHandler extends RuntimeException {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(AutomationExceptionHandler.class));

    private AutomationExceptionHandler() {
    }

    public void handleException(Exception exception, String message) {
        AutomationException error = new AutomationException(exception.getClass().getSimpleName() + "::" + message, exception);
        throw error;
    }
    public void handleException(Throwable exception, String message){
        AutomationException error = new AutomationException(exception.getClass().getSimpleName(), exception);
        throw error;
    }
//    public void handleElementException(ElementV)
}
