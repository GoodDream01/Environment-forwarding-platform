package com.dt.common.utils.srping;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 1.本类是获取Spring上下文对象ApplicationContext的工具类
 * 
 * 2.需要在applicationContext.xml文件中配置此bean，以便让Spring启动时自动为我们注入ApplicationContext对象.
 *
 *　例：
 *
 *　　<!-- 这个bean主要是为了得到ApplicationContext 所以它不需要其它属性-->
 *
 *　　<bean class="org.ing.springutil.MyApplicationContextUtil"></bean>
 *
 *	或加入@Component注解也可以实现注入
 *
 * 3.调用getBean("beanName")方法来得到由Spring 管理所有对象
 * 
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	public static ApplicationContext getContext() {
		return context;
	}
	/**
	 * 调用getBean("beanName")方法来得到由Spring 管理所有对象
	 * @param beanName
	 * @return
	 */
	public final static Object getBean(String beanName){
		return context.getBean(beanName);
	}
	
	public final static Object getBean(String beanName, Class<?> requiredType) {
		return context.getBean(beanName, requiredType);
	}
}
