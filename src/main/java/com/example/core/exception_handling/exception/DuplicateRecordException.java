package com.example.core.exception_handling.exception;

public class DuplicateRecordException  extends RuntimeException{
    public DuplicateRecordException() {
        super();
    }

    public DuplicateRecordException(String message) {
        super(message);
    }
}
