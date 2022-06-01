package com.yssj.core.framework.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Description: <br/>
 * date: 2022/2/24 18:46<br/>
 *
 * @author Lisland<br />
 */
@Component
@WebFilter(urlPatterns = "/**", filterName = "ParamFilter", dispatcherTypes = DispatcherType.REQUEST)
public class ParamFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            com.yssj.core.framework.filter.ParameterRequestWrapper paramRequest = new com.yssj.core.framework.filter.ParameterRequestWrapper((HttpServletRequest) servletRequest);
            filterChain.doFilter(paramRequest, servletResponse);
        }

        @Override
        public void destroy() {

        }
}
