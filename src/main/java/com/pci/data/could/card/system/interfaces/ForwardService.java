/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	地铁演示后台服务端系统
 *
 * @Title: ForwardService.java 
 * @Prject: cloud-card-demo-system
 * @Package: com.pci.data.could.card.system.interfaces 
 * @Description: 地铁服务接口
 * @author: dzy   
 * @date: 2016年12月19日 上午11:25:13 
 * @version: V1.0   
 */
package com.pci.data.could.card.system.interfaces;

import com.pci.data.could.card.system.dto.PackageData;

/** 
 * @ClassName: ForwardService 
 * @Description: 地铁服务接口
 * @since: 0.0.1
 * @author: dzy
 * @date: 2016年12月19日 上午11:25:13  
 */
public interface ForwardService {

	/**
	 * @Title: execute 
	 * @Description: 处理转发的服务
	 * @since: 0.0.1
	 * @param interface_code	接口编码
	 * @param logSeq			演示系统流水号
	 * @param packageData		请求报文实体
	 * @return
	 */
	public String execute(String interface_code, String logSeq, 
			PackageData packageData);
	
}
