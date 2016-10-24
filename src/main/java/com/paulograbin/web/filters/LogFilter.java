package com.paulograbin.web.filters;

import org.apache.catalina.connector.RequestFacade;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.function.Consumer;

@Component
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String ipAddress = request.getRemoteHost();
        String userAgent = ((RequestFacade) request).getHeader("user-agent");
        String url = ((RequestFacade) request).getRequestURL().toString();

        System.out.println(url + " - " + ipAddress + " - " + userAgent);

        chain.doFilter(request, response);
    }

    public void destroy() {
        // do nothing
    }
}
