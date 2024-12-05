package com.tinqin.academy.rest.interceptors;

import com.tinqin.academy.rest.models.LocaleHeader;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
public class LocaleInterceptor implements HandlerInterceptor {

    private final LocaleHeader localeHeader;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String locale = Optional
                .ofNullable(request.getHeader("locale"))
                .orElse("en");
        localeHeader.setLocale(locale);
        return true;
    }
}
