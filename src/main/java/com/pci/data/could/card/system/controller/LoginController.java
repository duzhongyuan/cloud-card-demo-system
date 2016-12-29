/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	常用工具类项目
 *
 * @Title: LoginController.java 
 * @Prject: cloud-card-demo-system
 * @Package: com.pci.data.could.card.system.controller 
 * @Description: TODO
 * @author: dzy   
 * @date: 2016年12月29日 下午3:22:32 
 * @version: V1.0   
 */
package com.pci.data.could.card.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pci.data.could.card.system.tools.LogUtils;
import com.pci.data.could.card.system.tools.SerialNumGenerator;

/** 
 * @ClassName: LoginController 
 * @Description: TODO
 * @since: TODO
 * @author: dzy
 * @date: 2016年12月29日 下午3:22:32  
 */
@Controller
public class LoginController {
	
	private static LogUtils logger = new LogUtils(LoginController.class);
	
	@Value("#{systemPropertiesReader['username']}")
	private String username;
	
	@Value("#{systemPropertiesReader['password']}")
	private String password;

	@ResponseBody
	@RequestMapping("/login.do")
	public Map<String,String> login(HttpServletRequest request, String userName, String password){
		
		String logSeq = SerialNumGenerator.nextLogSeq();
		
//		logger.info("username:" + username + ",password:" + password);
		
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtils.isBlank(userName) && !StringUtils.isBlank(password)) {
			
			if ("admin".equalsIgnoreCase(userName) && "123456".equalsIgnoreCase(password)) {
				
				logger.info(logSeq, "用户名和密码输入正确.");
//				return new ModelAndView("index");
				map.put("code", "0");
				map.put("msg", "登录成功");
				
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(900);
				session.setAttribute("userName", userName);
				
			} else{
				logger.info(logSeq, "用户名或密码输入错误.");
//				return new ModelAndView("loginError");
				map.put("code", "-1");
				map.put("msg", "用户名或密码有误");
			}
			
		} else{
//			return new ModelAndView("loginError");
			logger.info(logSeq, "用户名或密码为空.");
			map.put("code", "-2");
			map.put("msg", "用户名或密码为空");
		}
		
		return map;
	}
	
}
