/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.Filters;

/**
 *
 * @author sumukh.r
 */
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class CrossScriptingFilter implements Filter {
	private static Logger logger = Logger.getLogger(CrossScriptingFilter.class);
    private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

        @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
//        //cache control headers start
//        HttpServletResponse httpResp = (HttpServletResponse) response;
//        httpResp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//        httpResp.setHeader("Pragma", "no-cache");
//        httpResp.setHeader("X-Frame-Options", "DENY");
//        httpResp.setHeader("Expires", "0");
//        //cache control headers end
//    	logger.info("Inlter CrossScriptingFilter  ...............");
//        chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
//        logger.info("Outlter CrossScriptingFilter ...............");
        
        
        chain.doFilter(request, response);
    }

}
