/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	POSP系统
 *
 * @Title: IOUtils.java 
 * @Prject: FinancialPlatform_ParamDownload
 * @Package: com.pci.FinancialPlatform.tools 
 * @Description: IO工具类
 * @author: dzy   
 * @date: 2016年11月8日 下午8:40:18 
 * @version: V1.0   
 */
package com.pci.data.could.card.system.tools;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/** 
 * @ClassName: IOUtils 
 * @Description: IO工具类
 * @since: 1.0.0
 * @author: dzy
 * @date: 2016年11月8日 下午8:40:18  
 */
public class IOUtils {

	private static LogUtils logger = new LogUtils(IOUtils.class);
	
	/**
	 * 默认的缓冲区大小
	 */
	private static final int DEFAULT_BUFFER_SIZE = 4096;
	
	public static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = null;
		try {
			output = new ByteArrayOutputStream();
			copy(input, output);
			return output.toByteArray();
		} finally {
			closeQuietly(output);
		}
	}
	
	/**
	 * @Title: copy 
	 * @Description: 将字节输入流拷贝到字节输出流
	 * @since: 1.0.0
	 * @param input		字节输入流
	 * @param output	字节输出流
	 * @return			字节数
	 * @throws IOException
	 */
	public static int copy(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		output.flush();
		return count;
	}
	
	/**
	 * @Title: closeQuietly 
	 * @Description: 关闭IO流
	 * @since: 1.0.0
	 * @param close
	 */
	public static void closeQuietly(Closeable close) {
		closeQuietly(close, "关闭IO流出错！");
	}
	
	/**
	 * @Title: closeQuietly 
	 * @Description: 关闭可关闭的对象(如IO流,连接对象等)
	 * @since: 1.0.0
	 * @param close		要关闭的对象
	 * @param errorMsg	关闭对象出现异常时打印的信息
	 */
	public static void closeQuietly(Closeable close, String errorMsg) {
		if (close != null) {
			try {
				close.close();
			} catch (IOException e) {
				logger.error(errorMsg, e);
			}
		}
	}
	
}
