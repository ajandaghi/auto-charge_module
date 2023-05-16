package ir.mapsa.autochargemodule.configuration;

import ir.mapsa.autochargemodule.externalservices.UserAuthorizer;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthorizationFilter implements Filter
{
    @Autowired
    private UserAuthorizer userAuthorizer;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if( (httpServletRequest.getHeader("Authorization") == null || userAuthorizer.checkTokenValidity(((HttpServletRequest) request).getHeader("Authorization")).getIsValid()))
        {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized - Please log in");
        }
        else
        {
            chain.doFilter(request, response);
        }
    }

//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
}