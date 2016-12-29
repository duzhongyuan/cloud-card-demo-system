/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	地铁演示后台服务端系统
 *
 * @Title: DispatcherServiceImpl.java 
 * @Prject: cloud-card-demo-system
 * @Package: com.pci.data.could.card.system.dispatch.impl 
 * @Description: 服务转发实现类
 * @author: dzy   
 * @date: 2016年12月19日 上午11:21:21 
 * @version: V1.0   
 */
package com.pci.data.could.card.system.dispatch.impl;

import com.pci.data.could.card.system.dispatch.DispatcherService;
import com.pci.data.could.card.system.dispatch.ForwardServiceHolder;
import com.pci.data.could.card.system.dto.PackageData;
import com.pci.data.could.card.system.interfaces.ForwardService;
import com.pci.data.could.card.system.tools.CustomStringUtils;
import com.pci.data.could.card.system.tools.LogUtils;

/** 
 * @ClassName: DispatcherServiceImpl 
 * @Description: 服务转发实现类
 * @since: 0.0.1
 * @author: dzy
 * @date: 2016年12月19日 上午11:21:21  
 */
public class DispatcherServiceImpl implements DispatcherService {

	private static LogUtils logger = new LogUtils(DispatcherServiceImpl.class);
	
	private ForwardServiceHolder forwardServiceHolder;
	
	public void setForwardServiceHolder(ForwardServiceHolder forwardServiceHolder) {
		this.forwardServiceHolder = forwardServiceHolder;
	}

	/* (non Javadoc) 
	 * @Title: dispatch
	 * @Description: 服务转发
	 * @param packageData
	 * @param interface_code
	 * @param logSeq
	 * @return 
	 * @see com.pci.data.could.card.system.dispatch.DispatcherService#dispatch(com.pci.data.could.card.system.dto.PackageData, java.lang.String, java.lang.String) 
	 */
	@Override
	public String dispatch(PackageData packageData, String interface_code, String logSeq) {
		// TODO Auto-generated method stub
		logger.info(logSeq, "开始执行转发服务:" + interface_code);
		String result = null;
		try {
			ForwardService service = forwardServiceHolder.getForwardService(interface_code);
			if (service == null) {
				result = "指定的服务[" + interface_code + "]不存在，请重新确定服务接口名称！";
				logger.error(logSeq, result);
			} else {
				result = service.execute(interface_code, logSeq, packageData);
				if (logger.isDebugEnabled()) {
					logger.debug(logSeq, CustomStringUtils.append("转发服务[", interface_code, "]后返回信息：", result));
				}
			}
		} catch (Exception e) {
			result = "转发服务[" + interface_code + "]出错！";
			logger.error(logSeq, result, e);
		}
		return result;
	}

}
