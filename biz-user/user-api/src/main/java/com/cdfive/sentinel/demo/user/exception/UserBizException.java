package com.cdfive.sentinel.demo.user.exception;

import com.cdfive.sentinel.demo.base.exception.BizException;

/**
 * @author cdfive
 */
public class UserBizException extends BizException {

    public UserBizException() {

    }

    public UserBizException(String message) {
        super(message);
    }

    public UserBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserBizException(Throwable cause) {
        super(cause);
    }
}
