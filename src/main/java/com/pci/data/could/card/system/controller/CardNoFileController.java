/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	地铁演示后台服务端系统
 *
 * @Title: CardNoFileController.java 
 * @Prject: cloud-card-demo-system
 * @Package: com.pci.data.could.card.system.controller 
 * @Description: 卡号配置修改控制器
 * @author: dzy   
 * @date: 2016年12月19日 下午8:10:10 
 * @version: V1.0   
 */
package com.pci.data.could.card.system.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pci.data.could.card.system.tools.FieldMessageUtils;
import com.pci.data.could.card.system.tools.LogUtils;
import com.pci.data.could.card.system.tools.SerialNumGenerator;

/** 
 * @ClassName: CardNoFileController 
 * @Description: 卡号配置修改控制器
 * @since: 0.0.1
 * @author: dzy
 * @date: 2016年12月19日 下午8:10:10  
 */
@Controller
public class CardNoFileController {

	private static LogUtils logger = new LogUtils(CardNoFileController.class);
	
	@ResponseBody
	@RequestMapping("/addCardNo.do")
	public Map<String, String> addCardNo(String cardno){
		
		Map<String, String> map = new HashMap<String, String>();
		String logSeq = SerialNumGenerator.nextLogSeq();
		
		logger.info(logSeq, "要添加的卡号:" + cardno);
		
		boolean add = false;
		
		if (null != cardno) {
			String[] cardNoArr = cardno.split(",");
			List<String> cardNoList = Arrays.asList(cardNoArr);
			add = FieldMessageUtils.addCardList(logSeq, cardNoList);
		}
		
		if (add) {
			map.put("result", "success");
		} else{
			map.put("result", "fail");
		}
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/modifyCardVersion.do")
	public Map<String, String> modifyCardVersion(String cardVersion){
		
		String logSeq = SerialNumGenerator.nextLogSeq();
		
		logger.info(logSeq, "版本号要修改为:" + cardVersion);
		
		Map<String, String> map = new HashMap<String, String>();
		
		boolean modify = FieldMessageUtils.modifyCardVersion(logSeq, cardVersion);
		
		if (modify) {
			map.put("result", "success");
		} else{
			map.put("result", "fail");
		}
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getCardNoAndVersion.do")
	public Map<String, Object> getCardNoList(){
		
		String logSeq = SerialNumGenerator.nextLogSeq();
		
		Set<String> cardSet = FieldMessageUtils.getCardSet();
		String cardVersion = FieldMessageUtils.getCardVersion();
		
		logger.info(logSeq, "当前卡号版本号为:" + cardVersion);
		logger.info(logSeq, "当前所有的卡号:" + cardSet);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("cardSet", cardSet);
		map.put("cardVersion", cardVersion);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/deleteCardNo.do")
	public Map<String, Object> deleteCardNo(String cardNo){
		
		String logSeq = SerialNumGenerator.nextLogSeq();
		
		logger.info(logSeq, "需要删除的卡号:" + cardNo);
		Map<String, Object> map = new HashMap<String, Object>();
		
		boolean delete = FieldMessageUtils.deleteCardNo(logSeq, cardNo);
		
		if (delete) {
			map.put("result", "success");
		} else{
			map.put("result", "fail");
		}
		
		return map;
	}
	
}
