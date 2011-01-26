package com.exadel.friendface.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.log4j.*;

/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/21/11
 * Time: 4:15 PM
 */

public class LoggingFilter implements Filter {
    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (filterConfig == null) {
            return;
        }

        Logger logger = Logger.getLogger("logger");

        HttpServletRequest httpServletRequest;
        HttpServletResponse httpServletResponse;

        StringBuilder requestStringBuilder = new StringBuilder();
        if (servletRequest instanceof HttpServletRequest) {
            httpServletRequest = (HttpServletRequest) servletRequest;
            requestStringBuilder.append("request [")
                    .append(httpServletRequest.getMethod())
                    .append("] ")
                    .append(httpServletRequest.getRequestURI());
        } else {
            requestStringBuilder.append("[Logger error]");
        }

        logger.info(requestStringBuilder.toString());

        filterChain.doFilter(servletRequest, servletResponse);

        StringBuilder responseStringBuilder = new StringBuilder();
        if (servletResponse instanceof HttpServletResponse) {
            httpServletResponse = (HttpServletResponse) servletResponse;
            responseStringBuilder.append("response [")
                    .append(httpServletResponse.getContentType())
                    .append("] ");
        } else {
            responseStringBuilder.append("[Logger error]");
        }

        logger.info(responseStringBuilder.toString());
    }

    public void destroy() {
        this.filterConfig = null;
    }
}
