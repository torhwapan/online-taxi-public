package com.lichuanqi.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    /**
     * 拦截器，可以做token的校验
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


       //从请求头里取出token，然后解析
        String token = request.getHeader("Authorization");


        System.out.println("走拦截器了吗？  走了");

        return true;
    }
}
