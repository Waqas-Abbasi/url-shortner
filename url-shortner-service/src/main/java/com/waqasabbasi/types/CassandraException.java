package com.waqasabbasi.types;

public class CassandraException extends ErrorException {

    public CassandraException() {
        super();
    }

    public CassandraException(String errorCause) {
        super(errorCause);
    }

    @Override
    public String getMessage() {
        String cause = !super.getExceptionCause().equals("") ? "=> " + super.getExceptionCause() : "";
        return "Unable to Connect to Cassandra " + cause;
    }
}
