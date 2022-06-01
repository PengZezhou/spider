package com.yssj.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Administrator
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //允许跨域路由
        registry.addMapping("/**")
                //跨域域名
                .allowedOriginPatterns("*")
                //是否允许证书
                .allowCredentials(true)
                .allowedHeaders("*")
                //方法
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                //跨域过期时间
                .maxAge(3600);
    }
}
