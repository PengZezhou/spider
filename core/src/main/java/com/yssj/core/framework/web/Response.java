package com.yssj.core.framework.web;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.MDC;
import org.springframework.context.annotation.DependsOn;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@DependsOn
public class Response<T> {

    private final String code;
    private final String msg;
    private final T data;
    private final String requestId;

    private final Boolean success;

    private Response(RestStatus restStatus, String msg, T data) {
        this.code = restStatus.code();
        this.msg = msg;
        this.data = data;
        this.success = StrUtil.equals(RestStatus.OK.code(), restStatus.code());
        this.requestId = MDC.get("requestId");
    }

    private Response(RestStatus restStatus, T data) {
        this.code = restStatus.code();
        this.msg = restStatus.msg();
        this.data = data;
        this.success = StrUtil.equals(RestStatus.OK.code(), restStatus.code());
        this.requestId = MDC.get("requestId");
    }

    public static <T> Response<T> ok(T data, String msg) {
        return new Response<>(RestStatus.OK, msg, data);
    }

    public static <T> Response<T> ok(T data) {
        return new Response<>(RestStatus.OK, data);
    }

    public static <Void> Response<Void> ok() {
        return new Response<Void>(RestStatus.OK, null);
    }

    public static <T> Response<T> error(T data) {
        if(data instanceof RestStatus){
            RestStatus restStatus = (RestStatus)data;
            return new Response<>(restStatus,null);
        }
        return new Response<>(RestStatus.ERROR, data);
    }

    public static <T> Response<T> error(RestStatus restStatus, T data) {
        return new Response<>(restStatus, data);
    }

    public static <T> Response<T> error(RestStatus restStatus, String msg) {
        return new Response<>(restStatus, msg, null);
    }

}
