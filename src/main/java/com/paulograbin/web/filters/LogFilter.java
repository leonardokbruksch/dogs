package com.paulograbin.web.filters;

import org.apache.catalina.connector.RequestFacade;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String ipAddress = request.getRemoteHost();

        RequestFacade req = (RequestFacade) request;

        String userAgent = req.getHeader("user-agent");
        String url = req.getRequestURL().toString();

        System.out.println(url + " - " + ipAddress + " - " + userAgent);

        chain.doFilter(request, response);
    }

    public void destroy() {
        // do nothing
    }
}
