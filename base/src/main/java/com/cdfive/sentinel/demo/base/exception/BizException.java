package com.cdfive.sentinel.demo.base.exception;

/**
 * @author cdfive
 */
public class BizException extends RuntimeException {

    public BizException() {

    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }
}
