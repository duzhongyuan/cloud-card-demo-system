/**   
 * Copyright © 2016 广州佳都数据服务有限公司. All rights reserved.
 * Company 广州佳都数据服务有限公司	地铁演示后台服务端系统
 *
 * @Title: PackageData.java 
 * @Prject: cloud-card-demo-system
 * @Package: com.pci.data.could.card.system.dto 
 * @Description: 协议包数据
 * @author: dzy   
 * @date: 2016年12月19日 上午10:16:42 
 * @version: V1.0   
 */
package com.pci.data.could.card.system.dto;

import java.io.Serializable;

/** 
 * @ClassName: PackageData 
 * @Description: 协议包数据
 * @since: 0.0.1
 * @author: dzy
 * @date: 2016年12月19日 上午10:16:42  
 */
public class PackageData implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 序列版本ID
	 */
	private static final long serialVersionUID = 2052714496003214903L;

	/** 接口编码 **/
	private String interface_code;
	/** 终端编号 **/
	private String terminal_no;
	/** 请求时间 **/
	private String req_time;
	/** 请求版本号 **/
	private String card_version;
	public String getInterface_code() {
		return interface_code;
	}
	public void setInterface_code(String interface_code) {
		this.interface_code = interface_code;
	}
	public String getTerminal_no() {
		return terminal_no;
	}
	public void setTerminal_no(String terminal_no) {
		this.terminal_no = terminal_no;
	}
	public String getReq_time() {
		return req_time;
	}
	public void setReq_time(String req_time) {
		this.req_time = req_time;
	}
	public String getCard_version() {
		return card_version;
	}
	public void setCard_version(String card_version) {
		this.card_version = card_version;
	}
	
}
