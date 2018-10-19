package com.dy.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorHandler extends HandlerInterceptorAdapter {

    private static final Logger logger = LogManager.getLogger(InterceptorHandler.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
                response.sendRedirect("/dy/oauth/requestcode");
            } else {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userid")) {
                        return true;
                    }
                }
                response.sendRedirect("/dy/oauth/requestcode");
            }
            return false;
        } catch (Exception e) {
            logger.error("页面拦截器异常："+e.getMessage());
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {

    }
}
