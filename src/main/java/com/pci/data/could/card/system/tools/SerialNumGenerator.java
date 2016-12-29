/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	POSP系统
 *
 * @Title: SerialnoGenerator.java 
 * @Prject: FinancialPlatform_ParamDownload
 * @Package: com.pci.FinancialPlatform.tools 
 * @Description: 序列号生成器
 * @author: dzy   
 * @date: 2016年11月10日 上午10:14:33 
 * @version: V1.0   
 */
package com.pci.data.could.card.system.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/** 
 * @ClassName: SerialnoGenerator 
 * @Description: 序列号生成器
 * @since: 1.0.0
 * @author: dzy
 * @date: 2016年11月10日 上午10:14:33  
 */
public class SerialNumGenerator {
	
//	private static LogUtils logger = new LogUtils(SerialNumGenerator.class);
	
	private static final AtomicLong YKT_LOG_SEQ = new AtomicLong(1);		//黑名单主键的序列号
	
	public static String nextLogSeq(){
		StringBuffer buffer = new StringBuffer();
		buffer.append(CustomDateUtils.currentTimeToString12ForSecond());
		buffer.append(CustomConstants.SEQ_FIXED_SEPARATOR);
		buffer.append(String.format("%06d", YKT_LOG_SEQ.addAndGet(1)%1000000));
		return buffer.toString();
	}
	
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		
		FileWriter writer = new FileWriter("C:\\Users\\dzy\\Desktop\\test.txt");
		
//		writer.write("hello");
		
		for (long i = 0; i < 10; i++) {
//			System.out.println(i + ":" + II.addAndGet(1));
			writer.write(i + ":" + YKT_LOG_SEQ.addAndGet(1) + "\r\n");
		}
		
		writer.close();
		long end = System.currentTimeMillis();
		System.out.println("完成....耗时:" + (end - start));
	}
	
}
