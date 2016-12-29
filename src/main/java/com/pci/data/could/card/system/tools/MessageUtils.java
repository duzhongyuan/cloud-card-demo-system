/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	地铁演示后台服务端系统
 *
 * @Title: MessageUtils.java 
 * @Prject: cloud-card-demo-system
 * @Package: com.pci.data.could.card.system.tools 
 * @Description: TODO
 * @author: dzy   
 * @date: 2016年12月18日 下午4:39:37 
 * @version: V1.0   
 */
package com.pci.data.could.card.system.tools;

import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.MessageEvent;

import com.pci.data.could.card.system.dto.PackageData;

/** 
 * @ClassName: MessageUtils 
 * @Description: 报文处理工具类
 * @since: 1.0.0
 * @author: dzy
 * @date: 2016年12月18日 下午4:39:37  
 */
public class MessageUtils {

	private static LogUtils logger = new LogUtils(MessageUtils.class);
	
	public static boolean messageProcess(MessageEvent messageEvent, PackageData packageData, Charset charset){
		
		ChannelBuffer buf = (ChannelBuffer) messageEvent.getMessage();
		int packageLength = buf.readableBytes();
		//请求报文的长度不会小于28
		if (packageLength < 28) {
			logger.warn("接收到的消息格式错误,长度小于28.");
			// TODO Auto-generated method stub
			// 加上错误时要相应的内容
			
			
			
			return false;
		}
		
		String contentReceived = buf.toString(charset);
		
		logger.info("接收到的报文信息:" + contentReceived);
		
		String processResult = processPackage(packageData, contentReceived, charset.name());
		
		if (null != processResult && processResult.startsWith("fail")) {
			logger.warn("接收到的报文信息:" + contentReceived + "出错," + processResult.split("=")[1]);
			// TODO Auto-generated method stub
			// 加上错误时要相应的内容
			
			return false;
		}
		
		return true;
		
	}

	/** 
	 * @Title: processPackage 
	 * @Description: TODO
	 * @since: TODO
	 * @param packageData
	 * @param name
	 */
	private static String processPackage(PackageData packageData, String contentReceived, String name) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("开始处理接收到的报文......");
		}
		
		if (StringUtils.isBlank(contentReceived)) {
			logger.warn("数据报文为空.");
			return "fail=数据包结构为空";
		}
		
		String messageBody = contentReceived.substring(8);
		logger.info("报文体内容:" + messageBody);
		
		if (StringUtils.isBlank(messageBody)) {
			logger.warn("数据报文体内容为空.");
			return "fail=数据报文体内容为空";
		}
		
		String interface_code = messageBody.substring(0, 8);
		String terminal_no = messageBody.substring(8, 24);
		String req_time = messageBody.substring(24, 38);
		
		packageData.setInterface_code(interface_code);
		packageData.setTerminal_no(terminal_no);
		packageData.setReq_time(req_time);
		
		if (messageBody.length() > 38) {
			String card_version = messageBody.substring(38);
			packageData.setCard_version(card_version);
		}
		
//		String[] fields = messageBody.split(",");
//		int fieldNum = fields.length;	//报文体属性数量
//		
//		if (fieldNum >= 3) {
//			packageData.setInterface_code(fields[0]);
//			packageData.setTerminal_no(fields[1]);
//			packageData.setReq_time(fields[2]);
//			if (fieldNum > 3) {
//				packageData.setCard_version(fields[3]);
//			}
//		} else{
//			logger.warn("数据报文体内容属性小于3");
//			return "fail=数据报文体内容属性小于3";
//		}
		
		return "success";
	}
	
}
