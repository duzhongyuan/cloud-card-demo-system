/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	地铁演示后台服务端系统
 *
 * @Title: ModifyFileContent.java 
 * @Prject: cloud-card-demo-system
 * @Package: com.pci.data 
 * @Description: 修改文件内容测试类
 * @author: dzy   
 * @date: 2016年12月20日 上午10:06:47 
 * @version: V1.0   
 */
package com.pci.data;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

import org.junit.Test;

/** 
 * @ClassName: ModifyFileContent 
 * @Description: 修改文件内容测试类
 * @since: 0.0.1
 * @author: dzy
 * @date: 2016年12月20日 上午10:06:47  
 */
public class ModifyFileContent {

	@Test
	public void changeFile() throws Exception{
		String fileName = "D:/hello.txt";
		RandomAccessFile rf = new RandomAccessFile(fileName, "rw");
		
		String tempString = null;
		String firstString = "20161220";
		
		tempString = rf.readLine();
		
        System.out.println(tempString);
        System.out.println(firstString);
        rf.seek(0);
        rf.writeBytes(firstString);
 
        // rf.writeUTF(fristString);
 
        rf.close();
		
	}
	
	@Test
	public void changeFileHeader() throws Exception{
		String fileName = "D:/hello.txt";
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "gbk"));
		
		String newCardVersion = "20161222163706";
		
		String line = null;
		StringBuffer sb = new StringBuffer();
		//文件头第一行为卡号版本号,不做修改
		if (null != (line = reader.readLine())) {
			System.out.println("要修改的版本号:" + line + ",要修改为:" + newCardVersion);
			sb.append(newCardVersion);
		}
		
		while(null != (line = reader.readLine())){
			sb.append("\r\n").append(line);
		}
		
		reader.close();
		String content = sb.toString();
		
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "gbk"));
		writer.write(content);
		writer.close();
		System.out.println("modify cardVersion completed!");
		
	}
	
	@Test
	public void deleteFileContent() throws Exception{
		String fileName = "D:/hello.txt";
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "gbk"));
		String deleteCardNo = "4444444444444444";
		
		String line = null;
		StringBuffer sb = new StringBuffer();
		//文件头第一行为卡号版本号,不做修改
		if (null != (line = reader.readLine())) {
			sb.append(line);
		}
		
		while(null != (line = reader.readLine())){
			if (!deleteCardNo.equalsIgnoreCase(line)) {
				sb.append("\r\n").append(line);
			}
		}
		
		reader.close();
		String content = sb.toString();
		
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "gbk"));
		writer.write(content);
		writer.close();
		System.out.println("modify completed!");
	}
	
}
