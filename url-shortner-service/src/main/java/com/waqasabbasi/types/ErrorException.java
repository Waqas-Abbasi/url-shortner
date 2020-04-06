package com.waqasabbasi.types;

public abstract class ErrorException extends Exception {

    private String exceptionCause;

    public ErrorException(String exceptionCause) {
        this.exceptionCause = exceptionCause;
    }

    public ErrorException() {
        this.exceptionCause = "";
    }

    public String getExceptionCause() {
        return exceptionCause;
    }

    public abstract String getMessage();
}
