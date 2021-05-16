package com.cdfive.sentinel.demo.product.exception;

import com.cdfive.sentinel.demo.base.exception.BizException;

/**
 * @author cdfive
 */
public class ProductBizException extends BizException {

    public ProductBizException() {

    }

    public ProductBizException(String message) {
        super(message);
    }

    public ProductBizException(Throwable cause) {
        super(cause);
    }

    public ProductBizException(String message, Throwable cause) {
        super(message, cause);
    }
}
