package com.hsl.library.tool.exception;

public class MyException extends RuntimeException{
    private final String errorCode;
    private final Object[] errorArgs;

    public MyException() {
        this.errorCode = null;
        this.errorArgs = null;
    }

    public MyException(String message) {
        super(message);
        this.errorCode = null;
        this.errorArgs = null;
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = null;
        this.errorArgs = null;
    }

    public MyException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorArgs = null;
    }

    public MyException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorArgs = null;
    }

    public MyException(String errorCode, String message, Object... args) {
        super(String.format(message, args));
        this.errorCode = errorCode;
        this.errorArgs = args;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getErrorArgs() {
        return errorArgs;
    }
}