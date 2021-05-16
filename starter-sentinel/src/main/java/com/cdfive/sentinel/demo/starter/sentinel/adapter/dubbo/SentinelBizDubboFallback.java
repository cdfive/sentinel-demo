package com.cdfive.sentinel.demo.starter.sentinel.adapter.dubbo;

import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.cdfive.sentinel.demo.starter.sentinel.exception.SentinelBizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @author cdfive
 */
@Slf4j
@Component
public class SentinelBizDubboFallback implements DubboFallback {

    @Override
    public Result handle(Invoker<?> invoker, Invocation invocation, BlockException ex) {
        log.error("sentinel dubbo fallback,\nmethod={},\nrule={}", getInterfaceMethodAndArgument(invoker, invocation)
                , JSON.toJSONString(ex.getRule()), ex);
        return AsyncRpcResult.newDefaultAsyncResult(new SentinelBizException(JSON.toJSONString(ex.getRule())), invocation);
    }

    private String getInterfaceMethodAndArgument(Invoker<?> invoker, Invocation invocation) {
        String interfaceName = invoker.getInterface().getName();
        String methodName = invocation.getMethodName();
        StringBuilder arguments = new StringBuilder();
        if (!ObjectUtils.isEmpty(invocation.getArguments())) {
            boolean first = true;
            for (Object argument : invocation.getArguments()) {
                if (!first) {
                    arguments.append(",");
                } else {
                    first = false;
                }
                arguments.append(argument != null ? argument.toString() : null);
            }
        }
        String result = interfaceName + "." + methodName + "(" + arguments.toString() + ")";
        return result;
    }
}
