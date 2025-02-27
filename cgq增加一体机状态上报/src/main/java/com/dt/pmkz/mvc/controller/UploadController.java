package com.dt.pmkz.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RequestMapping("/upload1")
@Controller
public class UploadController {
	
	@RequestMapping(value ="/uploadfile1")
    @ResponseBody
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> fileList = multipartRequest.getFiles("file");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dayfolder = sdf.format(new Date());

        String url = "/uploadfiles/files/"+dayfolder+"/"; //应保证在根目录中有此目录的存在
        String temp = "/uploadfilesTemp/";
        String tempdir = request.getSession().getServletContext().getRealPath("/")+temp;
        String saveurl = request.getSession().getServletContext().getRealPath("/")+url;
       /*String tempdir = "D:\\Download\\temp\\"+dayfolder;
        String saveurl = "D:\\Download\\"+dayfolder;*/

        File saveurlFile = new File(saveurl);
        if(!saveurlFile.exists()){
            saveurlFile.mkdirs();
        }
        File tempFile = new File(tempdir);
        if(!tempFile.exists()){
            tempFile.mkdir();
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024); //设置缓冲区大小，这里是1M
        factory.setRepository(tempFile); //设置缓冲区目录

        String result = "";
        for (MultipartFile file1 : fileList) {
            String fileName = file1.getOriginalFilename();
            if (null == fileName || "".equals(fileName)){
                continue;
            }
            String extName = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String newFileName = df.format(new Date()) +new Random().nextInt(1000) + extName;
            File uploadedFile = new File(saveurl, newFileName);
            file1.transferTo(uploadedFile);
            result = saveurl+ newFileName+","+fileName;
        }
        return result;
    }

	
	@RequestMapping(value ="/uploadfile")
    @ResponseBody
	public String doPost(HttpServletRequest request, HttpServletResponse response)throws IOException {
		 System.out.println("111111");
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
        System.out.println("222222");
        String filePath = null;
        try {
            List<FileItem> items = upload.parseRequest(request);//获取所有文件列表
            System.out.println(items.size());
            for (FileItem item : items) {
                //获得文件名，这个文件名包括路径
                if(!item.isFormField()){
                    //文件名
                    String fileName = item.getName().toLowerCase();
                    String uuid = UUID.randomUUID().toString();
                    filePath = serverPath+uploadPath+uuid+fileName.substring(fileName.lastIndexOf("."));
                    item.write(new File(filePath));
              
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
  
        }
        return filePath;
    }
	
}
