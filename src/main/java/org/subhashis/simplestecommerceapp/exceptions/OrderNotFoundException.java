package org.subhashis.simplestecommerceapp.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String msg) {
        super(msg);
    }

    public OrderNotFoundException(Throwable cause) {
        super(cause);
    }

    public OrderNotFoundException(String msg, Throwable cause) {
        super(msg,cause);
    }
}
