/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	地铁演示后台服务端系统
 *
 * @Title: DispatcherService.java 
 * @Prject: cloud-card-demo-system
 * @Package: com.pci.data.could.card.system.dispatch 
 * @Description: 服务转发接口
 * @author: dzy   
 * @date: 2016年12月19日 上午11:15:21 
 * @version: V1.0   
 */
package com.pci.data.could.card.system.dispatch;

import com.pci.data.could.card.system.dto.PackageData;

/** 
 * @ClassName: DispatcherService 
 * @Description: 服务转发接口
 * @since: 0.0.1
 * @author: dzy
 * @date: 2016年12月19日 上午11:15:21  
 */
public interface DispatcherService {

	/**
	 * @Title: dispatch 
	 * @Description: 转发服务
	 * @since: 0.0.1
	 * @param packageData		报文体实体
	 * @param interface_code	接口编码
	 * @param logSeq			流水号
	 * @return
	 */
	public String dispatch(PackageData packageData, String interface_code, String logSeq);
	
}
