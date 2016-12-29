/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	常用工具类项目
 *
 * @Title: LoginInterceptor.java 
 * @Prject: cloud-card-demo-system
 * @Package: com.pci.data.could.card.system.interceptor 
 * @Description: 登录拦截器
 * @author: dzy   
 * @date: 2016年12月29日 下午2:14:56 
 * @version: V1.0   
 */
package com.pci.data.could.card.system.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.pci.data.could.card.system.tools.LogUtils;


/** 
 * @ClassName: LoginInterceptor 
 * @Description: 登录拦截器
 * @since: 0.0.1
 * @author: dzy
 * @date: 2016年12月29日 下午2:14:56  
 */
public class LoginFilter implements Filter {

	private static LogUtils logger = new LogUtils(LoginFilter.class);

	/* (non Javadoc) 
	 * @Title: init
	 * @Description: TODO
	 * @param filterConfig
	 * @throws ServletException 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig) 
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	/* (non Javadoc) 
	 * @Title: doFilter
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain) 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
        String userName = (String)session.getAttribute("userName");
        if(StringUtils.isBlank(userName)){
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            logger.info("用户未登录,需要登录.");
            httpResponse.sendRedirect("/login.jsp");
        }else{
        	chain.doFilter(request, response);
        }
	}

	/* (non Javadoc) 
	 * @Title: destroy
	 * @Description: TODO 
	 * @see javax.servlet.Filter#destroy() 
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	

	
}
