package com.tinqin.academy.rest.conf;

import com.tinqin.academy.rest.interceptors.LocaleInterceptor;
import com.tinqin.academy.rest.interceptors.LoggingInterceptor;
import com.tinqin.academy.rest.models.LocaleHeader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Bean
    @Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public LocaleHeader localeHeader() {
        return new LocaleHeader();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor());
        registry.addInterceptor(new LocaleInterceptor(localeHeader()));
    }
}
