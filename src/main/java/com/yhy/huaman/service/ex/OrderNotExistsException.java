package com.yhy.huaman.service.ex;

public class OrderNotExistsException extends ServiceException{
    public OrderNotExistsException() {
        super();
    }

    public OrderNotExistsException(String message) {
        super(message);
    }

    public OrderNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotExistsException(Throwable cause) {
        super(cause);
    }

    protected OrderNotExistsException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
