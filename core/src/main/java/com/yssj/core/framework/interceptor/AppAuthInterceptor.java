package com.yssj.core.framework.interceptor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.yssj.common.dto.response.YsesUserInfo;
import com.yssj.core.Context;
import com.yssj.core.framework.exception.AuthException;
import com.yssj.core.framework.web.RestStatus;
import com.yssj.core.module.sys.service.IYsesUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * APP 权限拦截器
 * @author Administrator
 */
@Component
public class AppAuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AppAuthInterceptor.class);

    @Autowired
    IYsesUserInfoService ysesUserInfoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = request.getServletPath();
        Context context = Context.get();
        context.setUri(path);
        if (notNeedToken(context)) {
            return true;
        }
        String token = request.getHeader("token");
        if(StrUtil.isBlank(token)){
            throw new AuthException(RestStatus.TOKEN_INVALID);
        }
        //校验token
        YsesUserInfo ysesUserInfo;
        try {
            ysesUserInfo = ysesUserInfoService.verifyToken(token);
        } catch (AuthException ex) {
            logger.error("token校验失败，uri: {}, token: {}", context.getUri(), token);
            throw new AuthException(RestStatus.AUTH_FAILURE);
        }

        /**
         * 其他验证逻辑
         */

        context.setToken(token);
        context.setUserId(ysesUserInfo.getUserId());
        context.setPhone(ysesUserInfo.getPhone());
        context.setUserName(ysesUserInfo.getUserName());
        return true;
    }

    private boolean notNeedToken(Context context) {
        String[] notIncludedUri = {
                ""
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
