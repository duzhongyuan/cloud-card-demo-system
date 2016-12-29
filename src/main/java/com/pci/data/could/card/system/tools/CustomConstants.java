package com.pci.data.could.card.system.tools;

/**
 * 常量类
 * 
 * @since 1.0.0
 * @author dzy
 */
public class CustomConstants {

	/** 字符编码：UTF-8 */
	public static final String CHARSET_UTF8 = "UTF-8";
	/** 字符编码：GBK */
	public static final String CHARSET_GBK = "GBK";
	
	/** 数据包类型：扁平包 */
	public static final String PACKTYPE_FLAT = "0";
	/** 数据包类型：复合包 */
	public static final String PACKTYPE_COMPOSITE = "1";
	
	/** 请求类型：查询 */
	public static final String REQTYPE_REQUEST = "0";
	/** 请求类型：回复 */
	public static final String REQTYPE_RESPONSE = "1";
	
	/** 回调前端的状态：成功 **/
	public static final String SCS_STATUS_SUCCESS = "S0A";
	/** 回调前端的状态：失败 **/
	public static final String SCS_STATUS_FAILURE = "S0F";
	/** 回调前端的状态：超时 **/
	public static final String SCS_STATUS_TIMEOUT = "S0E";
	
	/** 时间和序列之间加上0 **/
	public static final String SEQ_FIXED_SEPARATOR = "0";

	/** 地铁演示系统报文属性之间的间隔 **/
	public static final String FIELD_DELIMITER = ",";
	
	/** 卡号之间的分隔 **/
	public static final String CARD_NO_DELIMITER = "|";
}
