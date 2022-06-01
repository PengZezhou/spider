package com.yssj.core.framework.exception;

import com.yssj.core.framework.web.RestStatus;

public class AuthException extends RuntimeException {

    private static final long serialVersionUID = 7148145660883468514L;

    private String code;
    private String msg;

    public AuthException() {
    }

    public AuthException(Throwable ex) {
        super(ex);
    }

    public AuthException(String message) {
        super(message);
    }

    public AuthException(RestStatus restStatus, String msg) {
        this.code = restStatus.code();
        this.msg = msg;
    }

    public AuthException(RestStatus restStatus) {
        this.code = restStatus.code();
        this.msg = restStatus.msg();
    }

    public AuthException(String message, Throwable ex) {
        super(message, ex);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
