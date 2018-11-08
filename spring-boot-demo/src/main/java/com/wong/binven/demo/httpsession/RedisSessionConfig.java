package com.wong.binven.demo.httpsession;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

import lombok.Getter;
import lombok.Setter;

/**
 * create by: HuangZhiBin 
 * 2018年11月1日 下午3:20:51
 * 
 * @EnableRedisHttpSession 开启了Spring boot使用redis存储Session功能
 * 提供了集群Session共享支持
 */

@Getter@Setter
@Configuration
@EnableRedisHttpSession
@ConfigurationProperties("spring.cookie")
public class RedisSessionConfig {

	/** cookie名字 */
	private String name;
	
	/** 域 */
	private String domain;
	
	/** 存储路径 */
	private String path;
	
	/**
	 * cookie 子域session共享
	 * 
	 * @return
	 */
	@Bean
	public CookieSerializer cookieSerializer() {
		DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
		// cookie名字
		defaultCookieSerializer.setCookieName(name);
		// 域
		defaultCookieSerializer.setDomainName(domain);
		// 存储路径
		defaultCookieSerializer.setCookiePath(path);
		
		return defaultCookieSerializer;
	}

	@Bean
	public HttpSessionIdResolver headerSession() {
		// 默认使用CookieHttpSessionIdResolver保存Session Id
		return HeaderHttpSessionIdResolver.xAuthToken();
	}
	
	
//	监听Session的创建、销毁事件
	
//	方式1
//	@WebListener
//	public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener{
//	 public static final Logger logger= LoggerFactory.getLogger(SessionListener.class);
//	 
//	    @Override
//	    public void  attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
//	        logger.info("--attributeAdded--");
//	        HttpSession session=httpSessionBindingEvent.getSession();
//	        logger.info("key----:"+httpSessionBindingEvent.getName());
//	        logger.info("value---:"+httpSessionBindingEvent.getValue());
//	 
//	    }
//	 
//	    @Override
//	    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
//	logger.info("--attributeRemoved--");
//	}
//	 
//	    @Override
//	    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
//	logger.info("--attributeReplaced--");
//	    }
//	 
//	    @Override
//	    public void sessionCreated(HttpSessionEvent event) {
//	        logger.info("---sessionCreated----");
//	        HttpSession session = event.getSession();
//	        ServletContext application = session.getServletContext();
//	        // 在application范围由一个HashSet集保存所有的session
//	        HashSet sessions = (HashSet) application.getAttribute("sessions");
//	        if (sessions == null) {
//	            sessions = new HashSet();
//	            application.setAttribute("sessions", sessions);
//	        }
//	        // 新创建的session均添加到HashSet集中
//	       sessions.add(session);
//	        // 可以在别处从application范围中取出sessions集合
//	        // 然后使用sessions.size()获取当前活动的session数，即为“在线人数”
//	    }
//	 
//	    @Override
//	    public void sessionDestroyed(HttpSessionEvent event) throws ClassCastException {
//	        logger.info("---sessionDestroyed----");
//	        HttpSession session = event.getSession();
//	        logger.info("deletedSessionId: "+session.getId());
//	        System.out.println(session.getCreationTime());
//	        System.out.println(session.getLastAccessedTime());
//	        ServletContext application = session.getServletContext();
//	        HashSet sessions = (HashSet) application.getAttribute("sessions");
//	        // 销毁的session均从HashSet集中移除
//	        sessions.remove(session);
//	    }
//	 
//	}
	
//	方式2
//	@Bean
//	public ServletListenerRegistrationBean<MyHttpSessionListener> serssionListenerBean(){
//		ServletListenerRegistrationBean<MyHttpSessionListener> 
//		sessionListener = new ServletListenerRegistrationBean<MyHttpSessionListener>(new SessionListener());
//		return sessionListener;
//	}
}
