package com.yssj.core.framework.exception;

import com.yssj.core.framework.web.RestStatus;

public class SysException extends RuntimeException {

    private static final long serialVersionUID = 7148145660883468514L;

    private String code;
    private String msg;

    public SysException() {
    }

    public SysException(Throwable ex) {
        super(ex);
    }

    public SysException(String message) {
        super(message);
    }

    public SysException(RestStatus restStatus, String msg) {
        this.code = restStatus.code();
        this.msg = msg;
    }

    public SysException(RestStatus restStatus) {
        this.code = restStatus.code();
        this.msg = restStatus.msg();
    }

    public SysException(String message, Throwable ex) {
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
