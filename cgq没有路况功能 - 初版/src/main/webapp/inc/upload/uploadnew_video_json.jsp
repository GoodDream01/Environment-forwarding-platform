<%@page import="com.dt.common.utils.ComUtils"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException"%>
<%@ page import="org.apache.commons.fileupload.*" %>

  
<%
	 String picname="";

	 //获取操作类别
	 String cz=request.getParameter("cz");
	 if(cz==null){
	 	cz = "";
	 }

	 if(cz.equals("saveup")) {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		 String dayfolder = sdf.format(new Date());
         String url="/uploadfiles/videos/"+dayfolder+"/"; //应保证在根目录中有此目录的存在
         String temp = "/uploadfilesTemp/";
         String saveurl=application.getRealPath("/")+url;
         String tempdir = application.getRealPath("/")+temp;
         final long MAX_SIZE = 200 * 1024 * 1024;// 设置上传文件最大为 5M    
         // 允许上传的文件格式的列表    
         final String[] allowedExt = new String[] { "mp4" };      
         
         //判断目录是否存在，不存在就创建
         File saveurlFile = new File(saveurl);
         if(!saveurlFile.exists()){
        	 saveurlFile.mkdirs();
         }
         File tempFile = new File(tempdir);
         if(!tempFile.exists()){
       	  tempFile.mkdir();
         }
         // 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload    
          DiskFileItemFactory dfif = new DiskFileItemFactory();    
          dfif.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘    

          dfif.setRepository(tempFile);// 设置存放临时文件的目录,web根目录下的ImagesUploadTemp目录    
         
         // 用以上工厂实例化上传组件    
          ServletFileUpload sfu = new ServletFileUpload(dfif);    
         // 设置最大上传尺寸    
          sfu.setSizeMax(MAX_SIZE);   
          //这里就是中文文件名处理的代码，其实只有一行，serheaderencoding就可以了
          sfu.setHeaderEncoding("utf-8");
         
         // 从request得到 所有 上传域的列表    
          List fileList = null;    
         try {    
           fileList = sfu.parseRequest(request);    
          } catch (FileUploadException e) {// 处理文件尺寸过大异常    
          if (e instanceof FileUploadBase.SizeLimitExceededException) {     
            out.println("{success:false,message:'文件尺寸超过规定大小:" + MAX_SIZE + "字节'}");
           return;    
           }    
           e.printStackTrace();    
          }    
         // 没有文件上传    
         if (fileList == null || fileList.size() == 0) {    
             out.println("{success:false,message:'请选择上传文件'}"); 
          return;    
          }    
         // 得到所有上传的文件    
          Iterator fileItr = fileList.iterator();    
         // 循环处理所有文件    
         while (fileItr.hasNext()) {    
           FileItem fileItem = null;    
           String path = null;    
          long size = 0;    
          // 得到当前文件    
           fileItem = (FileItem) fileItr.next();    
          // 忽略简单form字段而不是上传域的文件域(<input type="text" />等)    
          if (fileItem == null || fileItem.isFormField()) {    
           continue;    
           }    
          // 得到文件的完整路径    
           path = fileItem.getName();       
          // 得到文件的大小    
           size = fileItem.getSize();    
          if ("".equals(path) || size == 0) {    
            out.println("{success:false,message:'请选择上传文件'}");
            return;    
           }    
         
          // 得到去除路径的文件名    
           String t_name = path.substring(path.lastIndexOf("\\") + 1);    
          // 得到文件的扩展名(无扩展名时将得到全名)    
           String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1);    
          // 拒绝接受规定文件格式之外的文件类型    
          int allowFlag = 0;    
          int allowedExtCount = allowedExt.length;    
          for (; allowFlag < allowedExtCount; allowFlag++) {    
              if (allowedExt[allowFlag].toLowerCase().equals(t_ext.toLowerCase())) //不区分大小写   
                  break;    
           }    
          if (allowFlag == allowedExtCount) {    

           String extStr = "";
           for (allowFlag = 0; allowFlag < allowedExtCount; allowFlag++)    
        	   extStr += "*." + allowedExt[allowFlag];    
            out.println("{success:false,message:'只允许上传"+extStr+"类型图片文件'}");
            return;    
           }    
         
          long now = System.currentTimeMillis();    
          // 根据系统时间生成上传后保存的文件名    
           String prefix = ComUtils.getUniqueString();    
          // 保存的最终文件完整路径,保存在web根目录下的ImagesUploaded目录下   
           picname = prefix + "." + t_ext;
           String u_name = saveurl+"/"+ picname;    
          try {    
           // 保存文件    
            fileItem.write(new File(u_name));    

    		String picUrl = url.replaceFirst("/", "")+picname;
    		
    		out.println("{success:true,message:'上传成功',url:'"+picUrl+"',fileName:'"+t_name+"',fileSize:'"+size+"',fileType:'."+t_ext+"'}");

           } catch (Exception e) {    
            e.printStackTrace();  
            out.println("{success:true,message:'上传失败'}");
           }
         }   

	 
	 } 

%>
