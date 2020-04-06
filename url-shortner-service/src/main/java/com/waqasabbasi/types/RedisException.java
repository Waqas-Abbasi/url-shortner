package com.waqasabbasi.types;

public class RedisException extends ErrorException {

    public RedisException() {
        super();
    }

    public RedisException(String errorCause) {
        super(errorCause);
    }

    @Override
    public String getMessage() {
        String cause = !super.getExceptionCause().equals("") ? "=> " + super.getExceptionCause() : "";
        return "Unable to Connect to Redis " + cause;
    }
}
