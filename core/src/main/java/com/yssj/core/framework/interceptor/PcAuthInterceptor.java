package com.yssj.core.framework.interceptor;

import cn.hutool.core.collection.CollUtil;
import com.yssj.core.Context;
import com.yssj.core.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * PC权限拦截器
 * @author Administrator
 */
@Component
public class PcAuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(PcAuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = request.getServletPath();
        Context context = Context.get();
        context.setUri(path);
        if (notNeedToken(context)) {
            return true;
        }
        String token = request.getHeader("token");
        String userId = TokenUtils.verifyToken(token);

        /**
         * 查询用户并校验是否可登录
          */
        context.setToken(token);
        context.setUserId(userId);
        context.setUserName("");
        return true;
    }


    private boolean notNeedToken(Context context) {
        String[] notIncludedUri = {
                "/admin/login",
                "/proposal/exportTemplate"
        };
        return CollUtil.contains(Arrays.asList(notIncludedUri), context.getUri());
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) {
        Context.clear();
    }
}
