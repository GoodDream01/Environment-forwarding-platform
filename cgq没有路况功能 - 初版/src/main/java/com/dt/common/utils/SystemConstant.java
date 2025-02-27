package com.dt.common.utils;

import java.io.IOException;
import java.util.Properties;

import com.dt.common.listener.Log4jListener;

public class SystemConstant {

	public static final String SYS_CONFIG_PROPERTIS = "/config/sysconfig.properties";
	public static String SES_USER = "currentUser";//session中存的用户登录信息的属性名字
	/**系统上下文路径*/
	public static String SysContextPath = System.getProperty(Log4jListener.contextPathkey);
	/**系统绝对路径*/
	public static String SysBaseDir = System.getProperty(Log4jListener.log4jdirkey);
	/**二维码logo图片*/
	public static String AudioPath = "uploadfiles/audios";
	/**默认分页数*/
	public static int pageSize = 20;
	
	/**二维码是否显示logo图片*/
	public static boolean QRCodeLogoFlag = false;
	/**二维码logo图片*/
	public static String QRCodeLogoIconPath = "/static/images/logo/logo.png";
	/**二维码大小*/
	public static int QRCodeSize = 7;
	
	/**************************************************/
	
	/**公钥*/
	public static String RsaPublicKey = "";
	/**秘钥*/
	public static String RsaPrivateKey = "";
	
	/**************************************************/

	
	static{
		Properties properties = new Properties();
		try {
			properties.load(SystemConstant.class.getResourceAsStream(SYS_CONFIG_PROPERTIS));
			QRCodeLogoFlag = Boolean.parseBoolean(properties.getProperty("QRCodeLogoFlag").toString().trim());
			QRCodeLogoIconPath = SysBaseDir+"/"+properties.getProperty("QRCodeLogoIconPath").toString().trim();
			QRCodeSize = Integer.parseInt(properties.getProperty("QRCodeSize").toString().trim());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
