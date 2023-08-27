package com.example.core.exception_handling.exception;

public class CustomAccessDeniedHandler  extends RuntimeException{
    public CustomAccessDeniedHandler() {
        super();
    }

    public CustomAccessDeniedHandler(String message) {
        super(message);
    }
}
