package com.cdfive.sentinel.demo.starter.sentinel.exception;

import lombok.Getter;
import lombok.Setter;
import org.apache.dubbo.rpc.RpcException;

/**
 * @author cdfive
 */
public class SentinelBizException extends RpcException {

    @Getter
    @Setter
    private String rule;

    public SentinelBizException(String message, String rule) {
        super(message);
        this.rule = rule;
    }

    public SentinelBizException() {

    }

    public SentinelBizException(String message) {
        super(message);
    }

    public SentinelBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public SentinelBizException(Throwable cause) {
        super(cause);
    }
}
