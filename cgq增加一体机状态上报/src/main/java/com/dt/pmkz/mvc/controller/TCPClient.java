package com.dt.pmkz.mvc.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.Socket;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.http.util.TextUtils;

public class TCPClient {
    
    public static void socketclient(Socket socket,String datastr) throws IOException{
        //创建Socket对象，连接服务器
        //Socket socket = new Socket(ip,dk);
        
        String dataarr[] = new String[datastr.length()/2];
        int count = 0;
        for(int i=0;i<datastr.length()-1;i=i+2) {
        	dataarr[count] = datastr.substring(i, i+2);
        	count++;
        }
        byte[] dd = Crc16Util.getData(dataarr);
        String str = Crc16Util.byteTo16String(dd).toUpperCase();
        
        String wzysj[] = str.split(" ");
        String zysj = "";
		for(int i=0;i<wzysj.length;i++) {
			if(wzysj[i].equals("02")) zysj = zysj + "1B E7 ";
			else if(wzysj[i].equals("03")) zysj = zysj + "1B E8 ";
			else if(wzysj[i].equals("1B")) zysj = zysj + "1B 00 ";
			else zysj = zysj + wzysj[i]+" ";
		}
        
        //通过客户端的套接字对象Socket方法，获取字节输出流，将数据写向服务器
		//System.out.println("02 "+zysj+" 03");
        OutputStream out = socket.getOutputStream();
        out.write(hexStrToBinaryStr("02 "+zysj+" 03"));
        
        //读取服务器发回的数据，使用socket套接字对象中的字节输入流
        InputStream in = socket.getInputStream();
        byte[] data = new byte[1024];
        int len = 0;
        if(in.available()!=0) {
        	len = in.read(data);
        	System.out.println("服务器说(不转)："+new String(data,0,len));
        	System.out.println("服务器说(转)："+BinaryToHexString(data));
        }
        
    }
    
	public static byte[] hexStrToBinaryStr(String hexString) {//将十六进制的字符串转换成字节数组
		if (TextUtils.isEmpty(hexString)) {
			return null;
		}
		hexString = hexString.replaceAll(" ", "");
		int len = hexString.length();
		int index = 0;
		byte[] bytes = new byte[len / 2];
		while (index < len) {
			String sub = hexString.substring(index, index + 2);
			bytes[index/2] = (byte)Integer.parseInt(sub,16);
			index += 2;
		}
		return bytes;
	}
	
	public static String BinaryToHexString(byte[] bytes) {//将字节数组转换成十六进制的字符串
	    String hexStr = "0123456789ABCDEF";
	    String result = "";
	    String hex = "";
	    for (byte b : bytes) {
	        hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));
	        hex += String.valueOf(hexStr.charAt(b & 0x0F));
	        result += hex + " ";
	    }
	    return result;
	}
	
	public static String stringToAscii(String value){//将字符串转成ASCII再转成16进制
	    StringBuffer sbu = new StringBuffer();  
	    char[] chars = value.toCharArray();   
	    for (int i = 0; i < chars.length; i++) {  
	        if(i != chars.length - 1){  
	            sbu.append(Integer.toHexString((int)chars[i])).append(" ");  
	        }else {  
	            sbu.append(Integer.toHexString((int)chars[i]));  
	        }  
	    }  
	    return sbu.toString();  
	}
	
	public static String AsciiToString(String asciiString){//将16进制ASCII转成10进制ASCII再转成字符
		StringBuffer sbu = new StringBuffer();
        for(int i=0;i<asciiString.length()-1;i=i+2) {
        	int data = Integer.parseInt(asciiString.substring(i, i+2),16);
        	sbu.append((char)data);
        }
		return sbu.toString();
	}
	
    public static String hexString2binaryString(String hexString) {//将16进制转换为二进制
        //16进制转10进制
        BigInteger sint = new BigInteger(hexString, 16);
        //10进制转2进制
        String result = sint.toString(2);
        //字符串反转
        return new StringBuilder(result).reverse().toString();
    }

    public static void writeData(String result,String fileName) {//写入文件
        String filePath = "C:\\Users\\Administrator\\Desktop";
        BufferedWriter out = null;
        try {
            File pathFile = new File(filePath);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }    
            String relFilePath = filePath + File.separator + fileName;
            File file = new File(relFilePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GB2312"));
        	out.write(result);
            out.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    static byte[] fileConvertToByteArray(File file) {//把一个文件转化为byte字节数组
        byte[] data = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            data = baos.toByteArray();
            fis.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
	
	public static byte[] imageToBytes(String url){//通过图片路径将图片文件转化为字符数组
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
	    BufferedImage bufferedImage = null;
	    try {
			bufferedImage = ImageIO.read(new File(url));
			ImageIO.write(bufferedImage,"bmp",byteOutput);
	        return byteOutput.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (byteOutput != null)
					byteOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
    public static void main(String[] args) throws IOException, InterruptedException {
		//1.取可变信息标志的当前故障
		//socketclient("192.168.80.218",5000,"30303031");
		//System.out.println(hexString2binaryString(AsciiToString("30303030")));
		
		//2.向可变信息标志上载文件：(play.lst)
		//System.out.println(stringToAscii("play001.lst"));
		//String str = "[playlist]\r\n" + 
				//"item_no = 1\r\n" + 
				//"item0 = 100, 1, 6, \\C000000\\fs3232\\c255000000000测试顶天001";
		//writeData(str,"play001.lst");
		//byte[] ss = fileConvertToByteArray(new File("C:\\Users\\Administrator\\Desktop\\play001.lst"));
		//System.out.println(BinaryToHexString(ss));
		//socketclient("192.168.80.218",5000,"30303130706c61792e6c73742b000000005B706C61796C6973745D0D0A6974656D5F6E6F203D20310D0A6974656D30203D203130302C20312C20362C205C433030303030305C6673333233325C63323535303030303030303030B6A5CCECB2E2CAD4");
		//System.out.println(AsciiToString("30"));
		
		//Socket socket = new Socket("192.168.80.218",5000);
	    //Socket socket = new Socket("127.0.0.1",8888);
		String datastr = "30303130"+stringToAscii("000.bmp").replaceAll(" ", "")+"2b";
		byte[] ss = imageToBytes("C:\\Users\\Administrator\\Desktop\\000.bmp");
		System.out.println(BinaryToHexString(ss));
		
		String filebyte = BinaryToHexString(ss).replaceAll(" ", "");
		int length = filebyte.length()/4096+1;
		for(int i=1;i<length+1;i++) {
			String hex = Integer.toHexString(2048*(i-1));
			String data = datastr+StringUtils.leftPad(hex, 8, "0");
			if(i==length) {
				data = data + filebyte.substring((i-1)*4096, filebyte.length());
			}else {
				data = data + filebyte.substring((i-1)*4096, i*4096);
			}
			//System.out.println("第几次："+i);
			System.out.println(data);
			//socketclient(socket,data);
		}
		
		//3.从可变信息标志下载文件：(play.lst)???
		//System.out.println(stringToAscii("play.lst"));//706c61792e6c7374
		//socketclient("192.168.80.218",5000,"30303039706c61792e6c737400000000");
		//System.out.println(AsciiToString("30"));
		
		//4.使可变信息标志显示预置的播放表：(001)
		//socketclient("192.168.80.218",5000,"30303938303031");
		//System.out.println(AsciiToString("30"));
		
		//5.取可变信息标志的当前显示内容
		//socketclient("192.168.80.218",5000,"30303937");
		//new String(data,0,len);
		//00987001000100006\C000000\fs3232\c255000000000江苏南京
		//序号987   停留时间00100    出字方式01    出字速度00006
		
		//6.设置亮度调节方式及亮度：手动（1）20
		//socketclient("192.168.80.218",5000,"30303033313230");
		//System.out.println(AsciiToString("30"));
		 
		//7.取可变信息标志的当前亮度调节方式和显示亮度
		//socketclient("192.168.80.218",5000,"30303036");
		//System.out.println(AsciiToString("313230"));
		
	}
	 
}