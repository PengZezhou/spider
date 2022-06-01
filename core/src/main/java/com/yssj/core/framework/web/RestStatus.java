package com.yssj.core.framework.web;

import cn.hutool.core.util.StrUtil;
import org.springframework.lang.Nullable;

/**
 * @author Administrator
 */

public enum RestStatus {
    /**
     * <p>Field : 访问失败-通用信息</p>
     */
    ERROR("500", "请求错误"),
    /**
     * <p>Field OK: 访问成功</p>
     */
    OK("200", "请求成功"),
    /**
     * <p>Field : 轮询登录接口</p>
     */
    TOKEN_INVALID("10010", "token无效"),
    TOKEN_EXPIRED("10011", "登录超时"),
    USER_FORBID("10012", "用户已禁用，请联系超管"),
    USER_CHECK_ERROR("10013", "用户名或密码错误"),
    USER_NOT_EXIST("10014", "用户不存在"),
    USER_PHONE_EXIST("10015", "手机号已存在"),
    AUTH_FAILURE("10020", "授权失败"),
    PARAM_ERROR("10030","参数错误"),
    OBJECT_NOT_FOUND("10040","对象不存在"),
    UNKNOWN_ERROR("00000", "未知错误，请联系管理员.");



    private static final RestStatus[] VALUES = values();
    private  final String code;
    private  final String msg;


    public String code() {
        return this.code;
    }
    public String msg() {
        return this.msg;
    }

    /**
     * <p>Description: 构造方法</p>
     *
     * @param code    枚举code
     * @param msg 枚举消息
     */
    private RestStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.code + " " + this.name();
    }

    public static RestStatus valueOfCode(String statusCode) {
        RestStatus status = resolve(statusCode);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
        } else {
            return status;
        }
    }

    @Nullable
    public static RestStatus resolve(String statusCode) {
        RestStatus[] var1 = VALUES;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            RestStatus status = var1[var3];
            if (StrUtil.equals(status.code,statusCode)) {
                return status;
            }
        }

        return null;
    }

    /**
     * 判断数值是否属于枚举类的值
     * @param code
     * @return boolean
     */
    public static boolean isInclude(String code) {
        boolean include = false;
        for (RestStatus e : RestStatus.values()) {
            if (e.code.equals(code)) {
                include = true;
                break;
            }
        }
        return include;
    }

    }
