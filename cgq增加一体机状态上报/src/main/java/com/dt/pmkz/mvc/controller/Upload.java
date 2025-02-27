package com.dt.pmkz.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.common.db.ConnDataBase;
import com.dt.common.utils.ComUtils;

@RequestMapping("/upload")
@Controller
public class Upload extends HttpServlet {

	@RequestMapping(value ="/uploadfile")
    @ResponseBody
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException {
		
		String devid = ""; 
		String filePath = null;
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    String dayfolder = sdf.format(new Date());

	    String uploadPath = "/uploadfiles/files/"+dayfolder+"/"; //应保证在根目录中有此目录的存在
	    String tempPath = "/uploadfilesTemp/";
	    String serverPath = null;
	    
       response.setContentType("text/html");
       response.setCharacterEncoding("utf-8"); //设置编码，方式返回的中文乱码
       
       serverPath = request.getSession().getServletContext().getRealPath("/");
       //Servlet初始化时执行,如果上传文件目录不存在则自动创建
       if(!new File(serverPath+uploadPath).isDirectory()){
           new File(serverPath+uploadPath).mkdirs();
       }
       if(!new File(serverPath+tempPath).isDirectory()){
           new File(serverPath+tempPath).mkdirs();
       }
       
       DiskFileItemFactory factory = new DiskFileItemFactory();
       factory.setSizeThreshold(5*1024); //最大缓存
       factory.setRepository(new File(serverPath+tempPath));//临时文件目录
       
       ServletFileUpload upload = new ServletFileUpload(factory);
       upload.setHeaderEncoding("UTF-8");
       try {
           List<FileItem> items = upload.parseRequest(request);//获取所有文件列表
           System.out.println("items.size()"+items.size());
           for (FileItem item : items) {
               if(!item.isFormField()){//判断是普通表单项还是文件上传项
            	   String fileName = item.getName().toLowerCase();//文件名
            	   SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            	   filePath = uploadPath+df.format(new Date()) +new Random().nextInt(1000)+fileName.substring(fileName.lastIndexOf("."));
                   item.write(new File(serverPath,filePath));
                   Snippet.h264ToMp4(serverPath+filePath);
                   filePath = filePath.replaceAll(".h264", ".mp4");
               }else {
            	   String paramName = item.getFieldName();
            	   if(paramName.equals("dev_id")) {
        			   devid = item.getString();
        		   }
               }
           }
           ConnDataBase bean = new ConnDataBase();
		   try {
				String sql = "insert into cgq_spxx(id,devid,splj,xtsj)values("
						+ "'"+ComUtils.getUniqueString()+"','"+devid+"','"+filePath+"',NOW())";
				bean.executeUpdate(sql);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       } catch (Exception e) {
           e.printStackTrace();
 
       }
     
   }
}