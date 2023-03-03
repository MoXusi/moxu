package com.awx.moxu.config;

import com.awx.moxu.utils.resolver.TokenArgumentResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author anLiHang
 * @date 2023/2/28
 */
@Configuration(
        proxyBeanMethods = false
)
@EnableCaching
public class WebMvcConfiguration implements WebMvcConfigurer {
    private static final Logger log = LoggerFactory.getLogger(WebMvcConfiguration.class);

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new TokenArgumentResolver());
    }
}
