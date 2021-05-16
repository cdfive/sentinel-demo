package com.cdfive.sentinel.demo.promotion.exception;

import com.cdfive.sentinel.demo.base.exception.BizException;

/**
 * @author cdfive
 */
public class PromotionBizException extends BizException {

    public PromotionBizException() {

    }

    public PromotionBizException(String message) {
        super(message);
    }

    public PromotionBizException(Throwable cause) {
        super(cause);
    }

    public PromotionBizException(String message, Throwable cause) {
        super(message, cause);
    }
}
