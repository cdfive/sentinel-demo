package com.cdfive.sentinel.demo.order.exception;

import com.cdfive.sentinel.demo.base.exception.BizException;

/**
 * @author cdfive
 */
public class OrderBizException extends BizException {

    public OrderBizException() {

    }

    public OrderBizException(String message) {
        super(message);
    }

    public OrderBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderBizException(Throwable cause) {
        super(cause);
    }
}
