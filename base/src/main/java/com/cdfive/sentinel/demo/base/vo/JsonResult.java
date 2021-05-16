package com.cdfive.sentinel.demo.base.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author cdfive
 */
public class JsonResult<T> {

    private static final Integer CODE_SUCCESS = 200;

    private static final Integer CODE_SERVER_ERROR = 500;

    private static final Integer CODE_BLOCK_ERROR = 600;

    private static final String MSG_BLOCK_ERROR = "前方拥挤，请稍候再试";

    private Integer code;

    private String msg;

    private T data;

    private Long ts;

    public static <T> JsonResult<T> success() {
        return new JsonResult<>(CODE_SUCCESS);
    }

    public static <T> JsonResult<T> success(String msg) {
        return new JsonResult<>(CODE_SUCCESS, msg);
    }

    public static <T> JsonResult<T> success(String msg, T data) {
        return new JsonResult<>(CODE_SUCCESS, msg, data);
    }

    public static <T> JsonResult<T> error() {
        return new JsonResult<>(CODE_SERVER_ERROR);
    }

    public static <T> JsonResult<T> error(String msg) {
        return new JsonResult<>(CODE_SERVER_ERROR, msg);
    }

    public static <T> JsonResult<T> error(String msg, T data) {
        return new JsonResult<>(CODE_SERVER_ERROR, msg, data);
    }

    public static <T> JsonResult<T> block() {
        return new JsonResult<>(CODE_BLOCK_ERROR, MSG_BLOCK_ERROR);
    }

    private JsonResult() {

    }

    public JsonResult(Integer code) {
        this.code = code;
        this.ts = System.currentTimeMillis();
    }

    private JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.ts = System.currentTimeMillis();
    }

    private JsonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.ts = System.currentTimeMillis();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public Long getTs() {
        return ts;
    }
}
