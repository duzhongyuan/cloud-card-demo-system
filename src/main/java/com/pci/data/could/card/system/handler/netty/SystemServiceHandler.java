/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	POSP系统
 *
 * @Title: SystemServiceHandler.java 
 * @Prject: cloud-card-demo-system
 * @Package: com.pci.data.could.card.system.handler.netty 
 * @Description: 消息处理器
 * @author: dzy   
 * @date: 2016年12月15日 下午2:54:43 
 * @version: V1.0   
 */
package com.pci.data.could.card.system.handler.netty;

import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.pci.data.could.card.system.dispatch.DispatcherService;
import com.pci.data.could.card.system.dto.PackageData;
import com.pci.data.could.card.system.tools.CustomConstants;
import com.pci.data.could.card.system.tools.CustomStringUtils;
import com.pci.data.could.card.system.tools.LogUtils;
import com.pci.data.could.card.system.tools.MessageUtils;
import com.pci.data.could.card.system.tools.SerialNumGenerator;

/** 
 * @ClassName: SystemServiceHandler 
 * @Description: 消息处理器
 * @since: 0.0.1
 * @author: dzy
 * @date: 2016年12月15日 下午2:54:43  
 */
public class SystemServiceHandler extends SimpleChannelUpstreamHandler {

	private static LogUtils logger = new LogUtils(SystemServiceHandler.class);
	
	private Charset charset;
	private DispatcherService dispatcherService;

	public SystemServiceHandler() {
		// 默认的字符编码为GBK
		this.charset = Charset.forName(CustomConstants.CHARSET_GBK);
	}
	
	public void setCharset(Charset charset) {
		this.charset = charset;
	}
	
	public void setDispatcherService(DispatcherService dispatcherService) {
		this.dispatcherService = dispatcherService;
	}

	/* (non Javadoc) 
	 * @Title: messageReceived
	 * @Description: 接收并出来请求消息
	 * @param ctx
	 * @param e
	 * @throws Exception 
	 * @see org.jboss.netty.channel.SimpleChannelUpstreamHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent) 
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent messageEvent) throws Exception {
		// TODO Auto-generated method stub
		//	接收并处理请求消息
//		super.messageReceived(ctx, e);
		PackageData packageData = new PackageData();
		try {
    		boolean result = MessageUtils.messageProcess(messageEvent, packageData, charset);
    		if (!result) {
    			
				return;
			}
		} catch (Exception e) {
    		logger.error("接收到的消息格式错误", e);
//       		ParamGroup group000 = new ParamGroup("000");
//    		group000.put("0001", "6544");
//    		group000.put("0002", "接收到的消息格式错误");
//    		String excepRes = DataPackageUtils.formResponseDataPackage("PGW0000", Constants.PACKTYPE_COMPOSITE, Constants.REQTYPE_RESPONSE, group000);
//    		ChannelBuffer resultBuf = ChannelBuffers.copiedBuffer(excepRes.getBytes(charset));
//        	messageEvent.getChannel().write(resultBuf);
    		//
//    		String errRespMsg = "";
    		
    		return;
		}
		
		String logSeq = SerialNumGenerator.nextLogSeq();	//生成一个流水
		
		ctx.setAttachment(logSeq);
		
		String interface_code = packageData.getInterface_code();
		logger.info(logSeq, "得到服务名称 [" + interface_code + "]");
		
		//转发服务
		String dispatchResult = null;
		
		try {
			dispatchResult = dispatcherService.dispatch(packageData, interface_code, logSeq);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		logger.info(logSeq, CustomStringUtils.append("转发服务[", interface_code, "]后的交易结果:", dispatchResult));
		
		int MessageLength = dispatchResult.length() + 8;	//报文体长度+报文长度值本身
		String finalResult = CustomStringUtils.leftFill(String.valueOf(MessageLength), '0', 8) + dispatchResult;
		
		logger.info(logSeq, "处理转发服务返回的交易结果：" + finalResult);
		
		if (StringUtils.isNotEmpty(finalResult)) {
			logger.info(logSeq, "成功返回处理后的交易结果：" + finalResult);
			ChannelBuffer resultBuf = ChannelBuffers.copiedBuffer(finalResult.getBytes(charset));
			messageEvent.getChannel().write(resultBuf);
			return;
			
		} else{
			// TODO Auto-generated catch block
			// 做一些错误相关的处理
		}
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		String logSeq = ctx.getAttachment() == null ? null : ctx.getAttachment().toString();
		logger.error(logSeq, "请求端已关闭或链接异常.", e.getCause());
		e.getChannel().close();
	}
}
