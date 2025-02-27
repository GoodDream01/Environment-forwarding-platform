package com.dt.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadUtils {
	
	public static void downloadFile(HttpServletRequest request,HttpServletResponse response,String fileUrl,String fileName){
	    OutputStream outp = null;
	    FileInputStream in = null;
		try
	    {
			response.reset();// 清空输出流
	    	response.setCharacterEncoding("UTF-8");
	    	
	    	String path = request.getContextPath();
	    	String basePath = request.getSession().getServletContext().getResource("/").getPath();
	    	basePath = request.getSession().getServletContext().getRealPath("/");
	    	
		    //response.setContentType("application/x-download");//设置为下载application/x-download
	    	response.setContentType("text/html;charset=utf-8");  
		    String filedownload = basePath+"/"+fileUrl;//即将下载的文件的相对路径
		    String filedisplay = fileName;//下载文件时显示的文件保存名称
		    filedisplay = URLEncoder.encode(filedisplay,"UTF-8");
	        //通知浏览器以下载的方式打开  
		    response.addHeader("Content-type", "appllication/octet-stream");  
		    response.addHeader("Content-Disposition","attachment;filename=" + filedisplay);



	        outp = response.getOutputStream();
	        File file1 = new File(filedownload);
	        in = new FileInputStream(file1);
	        BufferedInputStream buff = new BufferedInputStream(in);
	        InputStreamReader is =new InputStreamReader(in,"UTF-8");

	        byte[] b = new byte[1024];
	        int i = 0;

	        while((i = in.read(b)) !=-1)
	        {
	            outp.write(b, 0, i);
	        }
	        outp.flush();

		    
	    }
	    catch(Exception e)
	    {
	        System.out.println("Error!");
	        e.printStackTrace();
	    }
	    finally
	    {
	        if(in != null)
	        {
	            try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            in = null;
	        }
	        if(outp != null)
	        {
	            try {
					outp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            outp = null;
	        }
	    }
	}
	
}
