package com.yssj.core.config;

import com.yssj.core.framework.interceptor.AppAuthInterceptor;
import com.yssj.core.framework.interceptor.PcAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Administrator
 */
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private PcAuthInterceptor pcAuthInterceptor;
    @Autowired
    private AppAuthInterceptor appAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pcAuthInterceptor)
                .excludePathPatterns("/swagger**/**","/webjars**/**","/error","/**/app/**")
                .addPathPatterns("/**");
        registry.addInterceptor(appAuthInterceptor).addPathPatterns("/**/app/**");
    }

}
