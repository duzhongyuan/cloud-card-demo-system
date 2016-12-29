/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	地铁演示后台服务端系统
 *
 * @Title: CCDS0002.java 
 * @Prject: cloud-card-demo-system
 * @Package: com.pci.data.could.card.system.forward.service 
 * @Description: 获取逻辑卡号集合请求
 * @author: dzy   
 * @date: 2016年12月19日 下午1:48:43 
 * @version: V1.0   
 */
package com.pci.data.could.card.system.forward.service;

import java.util.Set;

import com.pci.data.could.card.system.dto.PackageData;
import com.pci.data.could.card.system.interfaces.ForwardService;
import com.pci.data.could.card.system.tools.CustomStringUtils;
import com.pci.data.could.card.system.tools.FieldMessageUtils;
import com.pci.data.could.card.system.tools.LogUtils;

/** 
 * @ClassName: CCDS0002 
 * @Description: 获取逻辑卡号集合请求
 * @since: 0.0.1
 * @author: dzy
 * @date: 2016年12月19日 下午1:48:43  
 */
public class CCDS0002 implements ForwardService {

	private static LogUtils logger = new LogUtils(CCDS0002.class);
	
	/* (non Javadoc) 
	 * @Title: execute
	 * @Description: 获取逻辑卡号集合
	 * @param interface_code
	 * @param logSeq
	 * @param packageData
	 * @return 
	 * @see com.pci.data.could.card.system.interfaces.ForwardService#execute(java.lang.String, java.lang.String, com.pci.data.could.card.system.dto.PackageData) 
	 */
	@Override
	public String execute(String interface_code, String logSeq, PackageData packageData) {
		
		String terminal_no = packageData.getTerminal_no();
		String req_time = packageData.getReq_time();
		String card_version = packageData.getCard_version();
		
		logger.info(logSeq, CustomStringUtils.append("接口编码:", interface_code, ",终端编号:", terminal_no, ",请求时间:", req_time, ",请求卡号版本号:", card_version));
		
		String return_code = "0000";	//返回响应码
		card_version = FieldMessageUtils.getCardVersion();	//获取当前版本号
		Set<String> cardSet = FieldMessageUtils.getCardSet();
		
		StringBuffer cardBuffer = new StringBuffer();
		
		int cardNum = cardSet.size();			//卡号数量
//		cardBuffer.append(CustomStringUtils.leftFill(String.valueOf(cardNum), '0', 8));		//8位定长数字
		
		for(String cardNo : cardSet) {
			cardBuffer.append(CustomStringUtils.leftFill(cardNo, '0', 16));
		}
		String card_nos = cardBuffer.toString();	//卡号集合
		
		StringBuffer sb = new StringBuffer();
		sb.append(interface_code)												//接口编码
		.append(CustomStringUtils.leftFill(terminal_no, '0', 16))				//终端编号
		.append(return_code)													//返回码
		.append(CustomStringUtils.leftFill(card_version, '0', 16))				//卡号版本号
		.append(CustomStringUtils.leftFill(String.valueOf(cardNum), '0', 8))	//卡号数量
		.append(card_nos);														//卡号集合
		
		String result = sb.toString();
		logger.info(logSeq, CustomStringUtils.append("服务接口:", interface_code, "服务端响应报文体内容:", result));
		
		return result;
	}

}
