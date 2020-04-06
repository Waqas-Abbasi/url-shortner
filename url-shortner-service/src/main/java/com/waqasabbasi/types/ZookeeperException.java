package com.waqasabbasi.types;


public class ZookeeperException extends ErrorException {

    public ZookeeperException(){
        super();
    }

    public ZookeeperException(String errorCause){
        super(errorCause);
    }

    @Override
    public String getMessage() {
        String cause = !super.getExceptionCause().equals("") ? "=> " + super.getExceptionCause() : "";
        return "Unable to Connect to Zookeeper " + cause;
    }
}
