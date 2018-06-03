/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.edu.cibertec.managed.LoginBean;

/**
 *
 * @author CHRISTIAN
 */
public class LoginFilter implements Filter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);
    public static final String LOGIN_PAGE = "/faces/login.xhtml";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("loginFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        LoginBean loginBean = (LoginBean) httpServletRequest.getSession().getAttribute("login");
        if (loginBean != null) {
            if (loginBean.isLoggedIn()) {
                LOGGER.debug("user is logged in");
                // user is logged in, continue request
                chain.doFilter(request, response);
            } else {
                LOGGER.debug("user is not logged in");
                // user is not logged in, redirect to login page
                httpServletResponse.sendRedirect(
                        httpServletRequest.getContextPath() + LOGIN_PAGE);
            }
        } else {
            LOGGER.debug("userManager not found");
            // user is not logged in, redirect to login page
            httpServletResponse.sendRedirect(
                    httpServletRequest.getContextPath() + LOGIN_PAGE);
        }
    }

    @Override
    public void destroy() {
    }
}
