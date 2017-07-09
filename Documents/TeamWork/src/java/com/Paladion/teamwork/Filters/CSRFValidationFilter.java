/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.Filters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
/**
 *
 * @author sumukh.r
 */
public class CSRFValidationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        // Assume its HTTP  
        HttpServletRequest httpReq = (HttpServletRequest)request;
        // Check the user session for the salt cache, if none is present we create one
        @SuppressWarnings("unchecked")
        Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>)
                httpReq.getSession().getAttribute("csrfPreventionSaltCache");

        System.out.println("Checking cahce befor creating it from Request :csrfPreventionSaltCache: "+csrfPreventionSaltCache);

        if(csrfPreventionSaltCache == null)
        {
            System.out.println("csrfPreventionSaltCache is null have to create new one");
            String csrfPreventionfromrequest = (String) httpReq.getSession().getAttribute("csrfPreventionSaltCache");
            System.out.println("csrfPreventionfromrequest :"+csrfPreventionfromrequest);

            // creating a new cache 
           csrfPreventionSaltCache = CacheBuilder.newBuilder().maximumSize(5000).expireAfterAccess(20, TimeUnit.MINUTES).build();

            // Setting to gttpReq
            httpReq.getSession().setAttribute("csrfPreventionSaltCache", csrfPreventionSaltCache);

            System.out.println("After setting the csrfPreventionSaltCache to HttpReq");
            System.out.println("--------csrfPreventionSaltCache------ :"+httpReq.getSession().getAttribute("csrfPreventionSaltCache"));
        }



        // Generate the salt and store it in the users cache
        String salt = RandomStringUtils.random(20, 0, 0, true, true, null, new SecureRandom());
        System.out.println("Salt: "+salt);
        csrfPreventionSaltCache.put(salt, Boolean.TRUE);
        // Add the salt to the current request so it can be used
        // by the page rendered in this request
        httpReq.setAttribute("csrfPreventionSalt", salt);

        System.out.println("Before going to validate salt checking for salt in request");
        System.out.println(" httpReq.getAttribute(csrfPreventionSalt) ----:"+httpReq.getAttribute("csrfPreventionSalt"));
        System.out.println(" httpReq.getSession().getAttribute(csrfPreventionSalt) :----"+httpReq.getSession().getAttribute("csrfPreventionSalt"));


        chain.doFilter(request, response);
    }
    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    /**
     *
     */
    public void destroy() {

    }
}