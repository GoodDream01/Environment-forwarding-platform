package com.dt.pmkz.mvc.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.pmkz.mvc.pojo.Pm;
import com.dt.pmkz.mvc.pojo.Yagl;
import com.dt.pmkz.mvc.service.PmService;
import com.dt.zxhygl.entity.AjaxJson;

@Controller
@RequestMapping("/pmkz/kz")
public class KzController {

	private static Logger logger = Logger.getLogger(KzController.class);
	
	@Resource
	private PmService service;
	
	@RequestMapping("save")
	@ResponseBody
	public AjaxJson save(@RequestBody Yagl t) throws UnknownHostException, IOException{
		logger.info("保存信息....");
		boolean success = false;
		Socket socket = new Socket(t.getIp(),t.getDk());
		String str = "";
		try {
			if(t.getUpload()!=null && !t.getUpload().equals("")) {
				BufferedImage image = ImageIO.read(new File(t.getUpload()));
				ImageIO.write(image, "BMP", new File("C:\\Users\\Administrator\\Desktop\\000.bmp"));
				String datastr = "30303130"+TCPClient.stringToAscii("000.bmp").replaceAll(" ", "")+"2b";
				byte[] ss = TCPClient.fileConvertToByteArray(new File("C:\\Users\\Administrator\\Desktop\\000.bmp"));
				String filebyte = TCPClient.BinaryToHexString(ss).replaceAll(" ", "");
				int length = filebyte.length()/4096+1;
				for(int i=1;i<length+1;i++) {
					String hex = Integer.toHexString(2048*(i-1));
					String data = datastr+StringUtils.leftPad(hex, 8, "0");
					if(i==length) {
						data = data + filebyte.substring((i-1)*4096, filebyte.length());
					}else {
						data = data + filebyte.substring((i-1)*4096, i*4096);
					}
					TCPClient.socketclient(socket,data);
				}
			}
			String datastr = t.getDz()+"3130"+"706c61792e6c73742b00000000";
			if(t.getUpload()!=null && !t.getUpload().equals("")) {
				str = "[playlist]\r\n" + 
						"item_no = 1\r\n" + 
						"item0 = "+t.getTlsj()+", "+t.getCzfs()+", "+t.getTxsd()+", "+"\\B000"
								+ "\\C000000\\fs"+t.getZtkg()+"\\c"+t.getZtys()+t.getWznr();
			}else {
				str = "[playlist]\r\n" + 
						"item_no = 1\r\n" + 
						"item0 = "+t.getTlsj()+", "+t.getCzfs()+", "+t.getTxsd()+", "
								+ "\\C000000\\fs"+t.getZtkg()+"\\c"+t.getZtys()+t.getWznr();
			}
			TCPClient.writeData(str,"play.lst");
			byte[] ss = TCPClient.fileConvertToByteArray(new File("C:\\Users\\Administrator\\Desktop\\play.lst"));
			datastr = (datastr+TCPClient.BinaryToHexString(ss)).replaceAll(" ", "");
		
			TCPClient.socketclient(socket,datastr);
		    success = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("保存失败....");
			logger.error(e.getMessage());
		}
		String msg = "保存失败！";
		if(success){
			msg = "保存成功！";
		}
		AjaxJson ajaxJson = new AjaxJson(success, msg);
		return ajaxJson;
	}

	@RequestMapping("cxxx")
	@ResponseBody
	public String cxxx(String id) throws UnknownHostException, IOException{
		String result = "";
		try {
			Pm pm = service.selectByPrimaryKey(id);
			Socket socket = new Socket(pm.getIp(),pm.getDk());
			String datastr = pm.getDz()+"3937";
	        
	        String dataarr[] = new String[datastr.length()/2];
	        int count = 0;
	        for(int i=0;i<datastr.length()-1;i=i+2) {
	        	dataarr[count] = datastr.substring(i, i+2);
	        	count++;
	        }
	        byte[] dd = Crc16Util.getData(dataarr);
	        String str = Crc16Util.byteTo16String(dd).toUpperCase();
	        
	        //通过客户端的套接字对象Socket方法，获取字节输出流，将数据写向服务器
	        OutputStream out = socket.getOutputStream();
	        out.write(TCPClient.hexStrToBinaryStr("02 "+str+" 03"));
	        
	        //读取服务器发回的数据，使用socket套接字对象中的字节输入流
	        InputStream in = socket.getInputStream();
	        byte[] data = new byte[1024];
	        int len = in.read(data);
	        result = new String(data,0,len);
	        System.out.println("服务器说："+new String(data,0,len));
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}
	
}
