/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	地铁演示后台服务端系统
 *
 * @Title: CCDS0001.java 
 * @Prject: cloud-card-demo-system
 * @Package: com.pci.data.could.card.system.forward.service 
 * @Description: 获取当前卡号版本号
 * @author: dzy   
 * @date: 2016年12月19日 下午12:17:09 
 * @version: V1.0   
 */
package com.pci.data.could.card.system.forward.service;

import com.pci.data.could.card.system.dto.PackageData;
import com.pci.data.could.card.system.interfaces.ForwardService;
import com.pci.data.could.card.system.tools.CustomStringUtils;
import com.pci.data.could.card.system.tools.FieldMessageUtils;
import com.pci.data.could.card.system.tools.LogUtils;

/** 
 * @ClassName: CCDS0001 
 * @Description: 获取当前卡号版本号
 * @since: 0.0.1
 * @author: dzy
 * @date: 2016年12月19日 下午12:17:09  
 */
public class CCDS0001 implements ForwardService{

	private static LogUtils logger = new LogUtils(CCDS0001.class);
	
	/* (non Javadoc) 
	 * @Title: execute
	 * @Description: 获取当前卡号版本号
	 * @param interface_code
	 * @param logSeq
	 * @param packageData
	 * @return 
	 * @see com.pci.data.could.card.system.interfaces.ForwardService#execute(java.lang.String, java.lang.String, com.pci.data.could.card.system.dto.PackageData) 
	 */
	@Override
	public String execute(String interface_code, String logSeq, PackageData packageData) {
		// TODO Auto-generated method stub
		String terminal_no = packageData.getTerminal_no();	//终端编号
		String req_time = packageData.getReq_time();		//请求时间
		
		logger.info(logSeq, CustomStringUtils.append("接口编码:", interface_code, ",终端编号:", terminal_no, ",请求时间:", req_time));
		
		String return_code = "0000";
		
		StringBuffer sb = new StringBuffer();
		sb.append(interface_code)
		.append(CustomStringUtils.leftFill(terminal_no, '0', 16))
		.append(return_code)
		.append(CustomStringUtils.leftFill(FieldMessageUtils.getCardVersion(), '0', 16))
		.append(CustomStringUtils.leftFill(String.valueOf(FieldMessageUtils.getcardNum()), '0', 8));
		
		String result = sb.toString();
		logger.info(logSeq, CustomStringUtils.append("服务接口:", interface_code, ",服务端响应报文体内容:", result));
		
		return result;
	}

}
