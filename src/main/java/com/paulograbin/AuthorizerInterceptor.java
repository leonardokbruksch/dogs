package com.paulograbin;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by I848568 on 17/05/2017.
 */
public class AuthorizerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object controller) throws Exception {

        String uri = request.getRequestURI();

        if(uri.endsWith("loginForm") ||
                uri.endsWith("doLogin") ||
                uri.endsWith("registerForm") ||
                uri.endsWith("register")) {
            return true;
        }

        if(request.getSession().getAttribute("loggedUser") != null) {
            return true;
        }
        response.sendRedirect("loginForm");
        return false;

    }
}