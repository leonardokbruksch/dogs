package com.leonardobruksch;

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

        if(uri.endsWith("dogsCockpit") &&  request.getSession().getAttribute("isAdminUser") == null ){
            response.sendRedirect("loginForm");
        }

        if(request.getSession().getAttribute("loggedUser") != null
                || request.getSession().getAttribute("isAdminUser") != null ) {
            return true;
        }
        response.sendRedirect("loginForm");
        return false;
    }
}