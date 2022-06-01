package com.yssj.core.framework.exception;

import cn.hutool.core.util.StrUtil;
import com.yssj.core.framework.web.Response;
import com.yssj.core.framework.web.RestStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@SuppressWarnings("ALL")
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    //自定义异常
    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public Response authExceptionHandler(AuthException ex) {
        if(RestStatus.isInclude(ex.getCode())){
            RestStatus restStatus = RestStatus.valueOfCode(ex.getCode());
            return Response.error(restStatus,ex.getMsg());
        }else{
            return Response.error(RestStatus.UNKNOWN_ERROR,ex.getMsg());
        }
    }

    @ExceptionHandler(SysException.class)
    @ResponseBody
    public Response sysExceptionHandler(SysException ex) {
        if(RestStatus.isInclude(ex.getCode())){
            RestStatus restStatus = RestStatus.valueOfCode(ex.getCode());
            return Response.error(restStatus,ex.getMsg());
        }else{
            return Response.error(RestStatus.UNKNOWN_ERROR,ex.getMsg());
        }

    }

    @ExceptionHandler(value = Exception.class)
    public Response exceptionHandler(Exception ex) {
        ex.printStackTrace();
        return Response.error(RestStatus.UNKNOWN_ERROR, StrUtil.isBlank(ex.getMessage())? RestStatus.UNKNOWN_ERROR.msg() : ex.getMessage());
    }

}
