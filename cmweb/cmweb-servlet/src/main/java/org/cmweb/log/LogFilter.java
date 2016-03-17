package org.cmweb.log;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by M.UNLU on 24.02.2016.
 */
public class LogFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("ip adresi "+servletRequest.getRemoteAddr());
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {

    }
}
