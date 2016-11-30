package com.shop.web.log;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LogFilter implements Filter {
	Logger logger = Logger.getLogger(LogFilter.class);
	// FilterConfig 用来访问filter的配置信息
	private FilterConfig config;

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.config = null;
	}

	/**
	 * 过滤器的核心方法
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// -------------下面代码用于对用户请求执行预处理操作---
		// 获取ServletContext,用于记录日志信息
		ServletContext context = this.config.getServletContext();
		Long before = System.currentTimeMillis();
		context.log("开始过滤……");
		//将请求转化为http请求
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		//获取用户的请求地址
		String servletPath = httpRequest.getServletPath();
		context.log("Filter已拦截到获取用户的请求地址" + servletPath);
		
		String contextPath = httpRequest.getContextPath();
		context.log("Filter已拦截到工程地址" + contextPath);
		/**
		 * pass the request along the filter chain
		 * 执行该方法之前，即对用户请求进行预处理；执行该方法之后，即对服务器响应进行后处理
		 */
		chain.doFilter(request, response);
		//---------下面代码用于对服务器响应执行后处理---------
		long after = System.currentTimeMillis();
		//记录日志
		context.log("过滤结束");
		//再次记录日志
		context.log("请求被定位到" + httpRequest.getRequestURI() + "所花的时间为: " + (after - before)); 
	}

}