package com.elu.profileservicesuperfs.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Configuration
public class FeignClientConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        String token = getCurrentToken(); // You must implement this
        if (token != null) {
            template.header("Authorization",  token);
        }
    }

    private String getCurrentToken() {
        // Extract token from current request context
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            return request.getHeader("Authorization");
        }
        return null;
    }
}
