/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	地铁演示后台服务端系统
 *
 * @Title: FieldMessageUtils.java 
 * @Prject: cloud-card-demo-system
 * @Package: com.pci.data.could.card.system.tools 
 * @Description: 返回给请求端的信息工具类
 * @author: dzy   
 * @date: 2016年12月19日 下午1:07:21 
 * @version: V1.0   
 */
package com.pci.data.could.card.system.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/** 
 * @ClassName: FieldMessageUtils 
 * @Description: 返回给请求端的信息工具类
 * @since: 0.0.1
 * @author: dzy
 * @date: 2016年12月19日 下午1:07:21  
 */
public class FieldMessageUtils {

	private static LogUtils logger = new LogUtils(FieldMessageUtils.class);
	
	private static String cardVersion = "2016122016161600";		//逻辑卡版本号
	private static Set<String> cardSet;					//逻辑卡号集合
	private static String cardFilePath;					//卡文件路径
	
	/**
	 * @Title: getCardVersion 
	 * @Description: 获取逻辑卡版本号
	 * @since: 0.0.1
	 * @return
	 */
	public static synchronized String getCardVersion(){
		return cardVersion.trim();
	}
	
	/**
	 * @Title: getcardNum 
	 * @Description: 获取当前逻辑卡号数量
	 * @since: 0.0.1
	 * @return
	 */
	public static synchronized int getcardNum(){
		return cardSet.size();
	}
	
	/**
	 * @Title: getCardList 
	 * @Description: 获取卡号列表
	 * @since: 0.0.1
	 * @return
	 */
	public static synchronized Set<String> getCardSet(){
		return cardSet;
	}
	
	/**
	 * @Title: modifyCardVersion 
	 * @Description: 修改卡号版本号
	 * @since: 0.0.1
	 * @param logSeq			流水号
	 * @param setcardVersion	要修改的版本号
	 * @return
	 */
	public static synchronized boolean modifyCardVersion(String logSeq, String setcardVersion){
		cardVersion = setcardVersion;
		
		logger.info(logSeq, "开始修改卡号版本为:" + setcardVersion);
		
//		String cardFilePath = FieldMessageUtils.class.getClassLoader().getResource("cardFile/cardno.txt").getPath();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(cardFilePath), "gbk"));
			
			String line = null;
			StringBuffer sb = new StringBuffer();
			//文件头第一行为卡号版本号,不做修改
			if (null != (line = reader.readLine())) {
				System.out.println("要修改的版本号:" + line + ",要修改为:" + setcardVersion);
				sb.append(setcardVersion);
			}
			
			while(null != (line = reader.readLine())){
				sb.append("\r\n").append(line);
			}
			
			reader.close();
			String content = sb.toString();
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cardFilePath), "gbk"));
			writer.write(content);
			writer.close();
			
			logger.info(logSeq, "版本号修改成功!");
			
		} catch (Exception e) {
			logger.error(logSeq, "修改卡号版本出现异常:", e);
			return false;
		}
		
		return true;
	}
	
	/**
	 * @Title: addCardList 
	 * @Description: 添加卡号
	 * @since: 0.0.1
	 * @param logSeq		流水号
	 * @param addCardList	要添加的卡号集合
	 * @return
	 */
	public static synchronized boolean addCardList(String logSeq, List<String> addCardList){
		if (null != addCardList && addCardList.size() > 0) {
			
			Set<String> needAddCardSet = new HashSet<String>();
			
			for (String cardno : addCardList) {
				if (!cardSet.contains(cardno)) {
					needAddCardSet.add(cardno);
				}
				cardSet.add(cardno);
			}
			
			StringBuffer sb = new StringBuffer();
			
			for(String cardno : needAddCardSet){
				sb.append("\r\n").append(cardno);
			}
			
			String writeContent = sb.toString();
//			String cardFilePath = FieldMessageUtils.class.getClassLoader().getResource("cardFile/cardno.txt").getPath();
			try {
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cardFilePath, true), CustomConstants.CHARSET_GBK));
				writer.write(writeContent);
				writer.flush();
				writer.close();
				logger.info(logSeq, "卡号:" + needAddCardSet + "写入文件成功！");
			}  catch (Exception e) {
				logger.error(logSeq, "将添加的卡号写到文件时出现异常:", e);
			}
			
			return true;
		}
		return false;
	}
	
	public static synchronized boolean deleteCardNo(String logSeq, String cardNo){
		
		boolean delete = false;
		
		logger.info(logSeq, "要删除的卡号:" + cardNo);
		
		//将卡号从Set里面移除
		boolean remove = cardSet.remove(cardNo);
		if (remove) {
			logger.info(logSeq, "卡号:" + cardNo + "从内存移除成功!");
		} else{
			logger.info(logSeq, "卡号:" + cardNo + "从内存移除失败!");
		}
		
//		String cardFilePath = FieldMessageUtils.class.getClassLoader().getResource("cardFile/cardno.txt").getPath();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(cardFilePath), "gbk"));
			String line = null;
			
			StringBuffer sb = new StringBuffer();
			//文件头第一行为卡号版本号,不做修改
			if (null != (line = reader.readLine())) {
				sb.append(line);
			}
			
			while(null != (line = reader.readLine())){
				if (!cardNo.equalsIgnoreCase(line)) {
					sb.append("\r\n").append(line);
				}
			}
			
			reader.close();
			String content = sb.toString();
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cardFilePath), "gbk"));
			writer.write(content);
			writer.close();
			
			delete = true;
			
			logger.info(logSeq, "卡号删除成功!");
		} catch (Exception e) {
			logger.error(logSeq, "删除卡号时出现异常:", e);
		}
		
		return delete;
	}
	
	/**
	 * @Title: init 
	 * @Description: 卡号相关内容初始化
	 * @since: 0.0.1
	 */
	public static synchronized void init(String setcardFilePath){
		cardFilePath = setcardFilePath;
		logger.info(CustomStringUtils.append("获取卡版本及卡号存储文件的路径:[", cardFilePath, "]"));
		
		File cardFile = new File(cardFilePath);
		if (!cardFile.exists()) {
			logger.info(CustomStringUtils.append("需要自动生成卡号文件:[", cardFilePath, "]"));
			
			File parentFile = cardFile.getParentFile();
			if (!parentFile.exists()) {
				boolean generateParentDir = parentFile.mkdirs();
				String parentDir = parentFile.getPath();
				if (generateParentDir) {
					logger.info(CustomStringUtils.append("卡号文件的父目录:[", parentDir, "]不存在,创建成功."));
				} else{
					logger.info(CustomStringUtils.append("卡号文件的父目录:[", parentDir, "]不存在,创建失败."));
				}
				
			}
			
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cardFilePath), "gbk"));
				StringBuffer sb = new StringBuffer();
				sb.append(cardVersion);
				
				sb.append("\r\n").append("6666666666666666")
				.append("\r\n").append("8888888888888888");
				
				String initCardContent = sb.toString();
				
				writer.write(initCardContent);
				
				logger.info(CustomStringUtils.append("自动生成卡号文件:[", cardFilePath, "]成功"));
			} catch (Exception e) {
				logger.error(CustomStringUtils.append("自动生成卡号文件:[", cardFilePath, "]时出现异常:"), e);
			} finally {
				IOUtils.closeQuietly(writer);
			}
			
		}
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(cardFilePath), "gbk"));
			String line = null;
			
			if(null != (line = reader.readLine())){
				cardVersion = line.trim();			//第一行记录卡号版本号
			}
			
			cardSet = new CopyOnWriteArraySet<String>();
			
			while(null != (line = reader.readLine())){
				if (!"".equals(line.trim())) {
					cardSet.add(line);		//将下面的卡号加入集合
				}
			}
			
		} catch (Exception e) {
			logger.error("加载卡号相关文件内容时出错!", e);
		} finally{
			IOUtils.closeQuietly(reader);
		}
		
	}
}
