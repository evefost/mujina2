DelegatingFilterProxyRegistrationBean implement ServletContextInitializer
代理filter 注册器，在这里用来注入spring-security proxy filter
说白了就是引用spring-security（springSecurityFilterChain） 会被启用
容器启动
EmbeddedWebApplicationContext
	private void selfInitialize(ServletContext servletContext) throws ServletException {
		prepareEmbeddedWebApplicationContext(servletContext);
		registerApplicationScope(servletContext);
		WebApplicationContextUtils.registerEnvironmentBeans(getBeanFactory(),
				servletContext);
			
		for (ServletContextInitializer beans : getServletContextInitializerBeans()) {
			beans.onStartup(servletContext);
		}
	}

DelegatingFilterProxy
initDelegate 把delegate 设置为FilterChainProxy
filter代理链

