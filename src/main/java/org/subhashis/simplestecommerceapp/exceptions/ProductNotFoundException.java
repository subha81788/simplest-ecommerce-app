package org.subhashis.simplestecommerceapp.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String msg) {
        super(msg);
    }

    public ProductNotFoundException(Throwable cause) {
        super(cause);
    }

    public ProductNotFoundException(String msg, Throwable cause) {
        super(msg,cause);
    }
}
