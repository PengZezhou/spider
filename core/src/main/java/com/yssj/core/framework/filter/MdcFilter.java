package com.yssj.core.framework.filter;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE + 100)
@Component
@WebFilter(filterName = "myMdcFilter", urlPatterns = "/*")
public class MdcFilter implements Filter {
    private static final String REQUEST_ID = "requestId";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestId = request.getParameter(REQUEST_ID);
        if (StringUtils.isBlank(requestId)) {
            requestId = IdUtil.fastUUID();
        }
        MDC.put(REQUEST_ID, requestId);
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove(REQUEST_ID);
        }
    }

    @Override
    public void destroy() {
    }
}