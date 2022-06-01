package com.yssj.core;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class Context {
    private String token;
    private String userId;
    private String phone;
    private String userName;
    private String uri;

    public static Context get() {
        Context context = contextThreadLocal.get();
        if (context == null) {
            context = new Context();
            contextThreadLocal.set(context);
        }
        return context;
    }
    public static void clear() {
        contextThreadLocal.set(null);
    }

    private static ThreadLocal<Context> contextThreadLocal = new ThreadLocal<>();
}
