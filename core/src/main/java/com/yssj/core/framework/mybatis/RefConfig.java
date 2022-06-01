package com.yssj.core.framework.mybatis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mapper.xml相关配置注入 
 *
 * @author Administrator
 */
@Configuration
public class RefConfig {
	
	@Value("${autof5:true}")
	boolean autof5;
	
	@Bean(name="MybatisMapperDynamicLoader")
	public MybatisMapperDynamicLoader get() {
		return new MybatisMapperDynamicLoader(autof5);
	}
	
}