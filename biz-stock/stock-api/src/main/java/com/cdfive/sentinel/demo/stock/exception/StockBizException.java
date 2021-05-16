package com.cdfive.sentinel.demo.stock.exception;

import com.cdfive.sentinel.demo.base.exception.BizException;

/**
 * @author cdfive
 */
public class StockBizException extends BizException {

    public StockBizException() {

    }

    public StockBizException(String message) {
        super(message);
    }

    public StockBizException(Throwable cause) {
        super(cause);
    }

    public StockBizException(String message, Throwable cause) {
        super(message, cause);
    }
}
