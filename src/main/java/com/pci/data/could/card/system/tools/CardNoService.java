/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	地铁演示后台服务端系统
 *
 * @Title: CardNoUtils.java 
 * @Prject: cloud-card-demo-system
 * @Package: com.pci.data.could.card.system.tools 
 * @Description: 卡号操作的工具类
 * @author: dzy   
 * @date: 2016年12月19日 下午4:39:05 
 * @version: V1.0   
 */
package com.pci.data.could.card.system.tools;

import org.springframework.beans.factory.annotation.Value;

/** 
 * @ClassName: CardNoService 
 * @Description: 卡号操作的服务
 * @since: 0.0.1
 * @author: dzy
 * @date: 2016年12月19日 下午4:39:05  
 */
public class CardNoService {

	private static LogUtils logger = new LogUtils(CardNoService.class);
	
	@Value("#{systemPropertiesReader['cardFilePath']}")
	private String cardFilePath;
	
	/**
	 * @Title: cardInit 
	 * @Description: 卡号相关内容初始化
	 * @since: 0.0.1
	 */
	public void cardInit(){
		logger.info("初始化卡号相关配置....");
		FieldMessageUtils.init(cardFilePath);
		logger.info(CustomStringUtils.append("卡号版本号:[", FieldMessageUtils.getCardVersion(), "],卡号数量:[", FieldMessageUtils.getcardNum(), "],卡号集合:", FieldMessageUtils.getCardSet()));
		logger.info("初始化卡号相关配置完成....");
	}
	
}
