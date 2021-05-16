package com.cdfive.sentinel.demo.app.rest.advice;

import com.cdfive.sentinel.demo.base.vo.JsonResult;
import com.cdfive.sentinel.demo.starter.sentinel.exception.SentinelBizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cdfive
 */
@Slf4j
@RestControllerAdvice
public class RespAppExceptionAdvice {

    @ExceptionHandler
    public <T> JsonResult<T> handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.error("RestApp error", e);

        if (e instanceof SentinelBizException) {
            return JsonResult.block();
        } else {
            return e != null ? JsonResult.error(e.getMessage()) : JsonResult.error();
        }
    }
}
