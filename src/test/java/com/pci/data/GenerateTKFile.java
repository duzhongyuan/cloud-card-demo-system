/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	POSP系统
 *
 * @Title: GenerateTKFile.java 
 * @Prject: cloud-card-demo-system
 * @Package: com.pci.data 
 * @Description: TODO
 * @author: dzy   
 * @date: 2016年12月17日 下午2:28:49 
 * @version: V1.0   
 */
package com.pci.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.pci.data.could.card.system.tools.CustomStringUtils;

/**
 * @ClassName: GenerateTKFile
 * @Description: TODO
 * @since: TODO
 * @author: dzy
 * @date: 2016年12月17日 下午2:28:49
 */
public class GenerateTKFile {

	@Test
	public void generateTKFile() throws IOException {
		String jyFileName = "C:\\Users\\dzy\\Desktop\\金管平台测试用例\\消费交易\\JY01201612170004.txt";
		String tkFileName = "C:\\Users\\dzy\\Desktop\\金管平台测试用例\\退货交易\\TK01201612170001.txt";
		String charsetName = "gbk";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(jyFileName), charsetName));
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(tkFileName), charsetName));

		String readLine = null;
		StringBuffer sb = new StringBuffer();

		int i = 0;

		if (null != reader && null != writer) {
			while (null != (readLine = reader.readLine())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

				String[] readFields = readLine.split("\\t");

				sb.append(readFields[0]).append("\t").append(readFields[1]).append("\t").append(readFields[2])
						.append("\t").append(sdf.format(new Date())).append("\t").append(readFields[7]).append("\t")
						.append(readFields[8]).append("\t").append(readFields[9]).append("\t").append(readFields[10])
						.append("\t").append(readFields[13]).append("\t").append(readFields[14]).append("\t")
						.append(readFields[15]).append("\t").append(readFields[16]).append("\t").append(readFields[17]);

				if (i > 0) {
					sb.append("\n");
				}
				i++;

			}
			String context = sb.toString();
			writer.write(context);
			writer.flush();
			writer.close();
			reader.close();
		}
		System.out.println(tkFileName + ":文件生成完毕！");

	}

	@Test
	public void generateJYFileName() throws IOException {
		String jyAllFileName = "C:\\Users\\dzy\\Desktop\\金管平台测试用例\\消费总交易\\JY01201612160002未发数据.TXT";

		String charsetName = "gbk";
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(jyAllFileName), charsetName));

		int fileNum = 20;
		int fileStart = 15;

		for (int i = 1; i < fileNum; i++) {

			String fileSeq = CustomStringUtils.leftFill("" + (fileStart + i), '0', 4);

			String tkFileName = "C:\\Users\\dzy\\Desktop\\金管平台测试用例\\消费交易\\生成的交易\\JY0120161217" + fileSeq + ".TXT";
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(tkFileName), charsetName));
			if (null != reader && null != writer) {
				StringBuffer sb = new StringBuffer();

				for (int j = 1; j <= 5; j++) {
					String line = reader.readLine();
					if (null == line) {
						break;
					}
					if (5 != j) {
						sb.append(line).append("\n");
					} else {
						sb.append(line);
					}
				}

				String context = sb.toString();
				writer.write(context);
				writer.flush();
				writer.close();
			}
			System.out.println(tkFileName + ":文件生成完毕！");

		}

		reader.close();
	}

	@Test
	public void generateTKSplitFile() throws IOException {
		String jyFileName = "C:\\Users\\dzy\\Desktop\\金管平台测试用例\\消费交易\\消费成功交易记录\\JY01201612140004\\JY01201612140004.TXT";
		// String tkFileName =
		// "C:\\Users\\dzy\\Desktop\\金管平台测试用例\\退货交易\\TK01201612170001.txt";
		String charsetName = "gbk";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(jyFileName), charsetName));
		// BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new
		// FileOutputStream(tkFileName), charsetName));

		int fileNum = 11;
		int fileStart = 1;

		for (int i = 1; i <= fileNum; i++) {

			String fileSeq = CustomStringUtils.leftFill("" + (fileStart + i), '0', 4);

			String tkFileName = "C:\\Users\\dzy\\Desktop\\金管平台测试用例\\退货交易\\生成的退货交易\\TK0120161218" + fileSeq + ".TXT";
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(tkFileName), charsetName));
			String readLine = null;

			StringBuffer sb = new StringBuffer();

			for (int j = 1; j <= 1; j++) {
				if (null != reader && null != writer) {
					if (null != (readLine = reader.readLine())) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

						String[] readFields = readLine.split("\\t");

						sb.append(readFields[0]).append("\t").append(readFields[1]).append("\t").append(readFields[2])
								.append("\t").append(sdf.format(new Date())).append("\t").append(readFields[7])
								.append("\t").append(readFields[8]).append("\t").append(readFields[9]).append("\t")
								.append(readFields[10]).append("\t").append(readFields[13]).append("\t")
								.append(readFields[14]).append("\t").append(readFields[15]).append("\t")
								.append(readFields[16]).append("\t").append(readFields[17]).append("\n");

					}
					String context = sb.toString();
					writer.write(context);
				}
				writer.flush();
				writer.close();
			}
			System.out.println(tkFileName + ":文件生成完毕！");

		}
		reader.close();
		System.out.println("文件全部生成....");

		// String readLine = null;
		// StringBuffer sb = new StringBuffer();
		//
		// int i = 0;
		//
		// if (null != reader && null != writer) {
		// while(null != (readLine = reader.readLine())){
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//
		// String[] readFields = readLine.split("\\t");
		//
		// sb.append(readFields[0]).append("\t")
		// .append(readFields[1]).append("\t")
		// .append(readFields[2]).append("\t")
		// .append(sdf.format(new Date())).append("\t")
		// .append(readFields[7]).append("\t")
		// .append(readFields[8]).append("\t")
		// .append(readFields[9]).append("\t")
		// .append(readFields[10]).append("\t")
		// .append(readFields[13]).append("\t")
		// .append(readFields[14]).append("\t")
		// .append(readFields[15]).append("\t")
		// .append(readFields[16]).append("\t")
		// .append(readFields[17]);
		//
		// if (i > 0) {
		// sb.append("\n");
		// }
		// i++;
		//
		// }
		// String context = sb.toString();
		// writer.write(context);
		// writer.flush();
		// writer.close();
		// reader.close();
		// }
		// System.out.println(tkFileName + ":文件生成完毕！");

	}

}
