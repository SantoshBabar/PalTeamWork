/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.Filters;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.RandomStringUtils;

public class CSRFTokenGenerator implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

HttpServletRequest httpReq = (HttpServletRequest)request;
        String url = httpReq.getRequestURL().toString();
        
        if(url.contains("Login")||url.contains("ResetPassword")||url.contains("ForgotPassword")||!(url.endsWith(".do"))){
            System.out.println("CSRF Token not generated -------------\n");
            chain.doFilter(request, response);
          }
        
        else{
            @SuppressWarnings("unchecked")
            Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>)httpReq.getSession().getAttribute("csrfPreventionSaltCache");
            
                if(csrfPreventionSaltCache == null)
                {
                    csrfPreventionSaltCache = CacheBuilder.newBuilder().maximumSize(5000).expireAfterAccess(20, TimeUnit.MINUTES).build();
                    httpReq.getSession().setAttribute("csrfPreventionSaltCache", csrfPreventionSaltCache);
                }
                System.out.println("CSRF Token generated -----------\n");
            String salt = RandomStringUtils.random(30, 0, 0, true, true, null, new SecureRandom());
            csrfPreventionSaltCache.put(salt, Boolean.TRUE);
            httpReq.setAttribute("csrfPreventionSalt", salt);
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}