/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	地铁演示后台服务端系统
 *
 * @Title: ForwarServiceHolder.java 
 * @Prject: cloud-card-demo-system
 * @Package: com.pci.data.could.card.system.dispatch 
 * @Description: TODO
 * @author: dzy   
 * @date: 2016年12月19日 上午11:31:16 
 * @version: V1.0   
 */
package com.pci.data.could.card.system.dispatch;

import java.util.HashMap;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.pci.data.could.card.system.interfaces.ForwardService;
import com.pci.data.could.card.system.tools.LogUtils;

/** 
 * @ClassName: ForwarServiceHolder 
 * @Description: TODO
 * @since: TODO
 * @author: dzy
 * @date: 2016年12月19日 上午11:31:16  
 */
public class ForwardServiceHolder implements ApplicationContextAware {

	private static LogUtils logger = new LogUtils(ForwardServiceHolder.class);
	
	private ApplicationContext applicationContext;
	private String serviceNameString;
	private String serviceNamespace;
	private HashMap<String, ForwardService> serviceCache;
	
	public void setServiceNameString(String serviceNameString) {
		this.serviceNameString = serviceNameString;
	}

	public void setServiceNamespace(String serviceNamespace) {
		this.serviceNamespace = serviceNamespace;
	}


	/* (non Javadoc) 
	 * @Title: setApplicationContext
	 * @Description: TODO
	 * @param arg0
	 * @throws BeansException 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext) 
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void init(){
		logger.info("Initializing forward services started. serviceNames=" + serviceNameString);
		if (serviceCache == null) {
			serviceCache = new HashMap<String, ForwardService>();
		} else {
			serviceCache.clear();
		}
		try {
			String[] serviceNames = serviceNameString.split(",");
			for (String serviceName : serviceNames) {
				loadForwardService(serviceName);
			}
			logger.info("Initializing forward services is complete. services=" + serviceCache);
		} catch (Exception e) {
			logger.error("Initializing forward services error. serviceNames=" + serviceNameString, e);
		}
		logger.info("Initializing forward services end. serviceNames=" + serviceNameString);
	}
	
	/**
	 * 加载服务对象
	 * @param serviceName
	 * @return
	 */
	private void loadForwardService(String serviceName) {
		// 先从spring容器中获取服务对象，如果服务对象不存在，则再创建服务对象
		Object service = null;
		try {
			service = applicationContext.getBean(serviceName);
		} catch (Exception e) {
			logger.error("Can not find the service[" + serviceName + "] from spring applicationContext.", e);
		}
		String serviceClassName = serviceNamespace + "." + serviceName;
		if (service != null && service.getClass().getName().equals(serviceClassName)) {
			serviceCache.put(serviceName, (ForwardService)service);
		} else {
			serviceCache.put(serviceName, newForwardService(serviceClassName));
		}
	}

	/**
	 * 实例化服务对象
	 * @param serviceClassName
	 * @return
	 */
	private ForwardService newForwardService(String serviceClassName) {
		try {
			Class<?> serviceClass = Class.forName(serviceClassName);
			return (ForwardService) serviceClass.newInstance();
		} catch (Exception e) {
			logger.error("To new the service instance error. serviceClassName=" + serviceClassName, e);
		}
		return null;
	}
	
	/**
	 * 获取服务对象
	 * @param serviceName
	 * @return
	 */
	public ForwardService getForwardService(String serviceName) {
		return serviceCache.get(serviceName);
	}

	/**
	 * 获取服务名称
	 * @return
	 */
	public String[] getServiceNames() {
		if (serviceCache == null || serviceCache.isEmpty()) {
			return null;
		}
		Set<String> serviceNames = serviceCache.keySet();
		return serviceNames.toArray(new String[serviceNames.size()]);
	}

}
