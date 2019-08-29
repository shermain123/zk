package com.cn.member.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;

/********声明*************
 *
 * 类    名称：LoadServletContextListener
 * 功能描述：
 *
 * 创建人员：zhougaoyun
 * 创建时间：2016-6-24
 * 版      本：
 ********修改记录************
 * 修改人员：
 * 修改时间：
 * 修改描述：
 */
@WebListener
public class LoadServletContextListener implements ServletContextListener{
	public static ApplicationContext context;
	private static final String ctx = "ctx";
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		String contextPath = application.getContextPath();
		application.setAttribute(ctx, contextPath);	
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		application.removeAttribute(ctx);
	}

}
