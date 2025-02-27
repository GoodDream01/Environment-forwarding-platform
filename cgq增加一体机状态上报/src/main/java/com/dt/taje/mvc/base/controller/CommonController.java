package com.dt.taje.mvc.base.controller;


import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.common.utils.DownloadUtils;
import com.dt.taje.utils.ComUtils;
import com.dt.taje.utils.ExportExcel;
import com.dt.taje.utils.RandomValidateCode;
import com.dt.taje.utils.Tools;
import com.dt.taje.utils.ZiDianUtils;
import com.dt.taje.utils.ui.JSON;
import com.dt.zxhygl.mvc.base.pojo.DictItem;
import com.dt.zxhygl.mvc.base.pojo.DictType;
import com.dt.zxhygl.mvc.base.service.IDictItemService;
import com.dt.zxhygl.mvc.base.service.IDictTypeService;
import com.dt.zxhygl.utils.DictUtils;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;





@Controller
@RequestMapping("/common")
public class CommonController {
	
	@Resource
	private IDictTypeService iDictTypeService ;
	
	@Resource
	private IDictItemService iDictItemService;
	
	@RequestMapping("/getDictItemByTypeCode")
	@ResponseBody
	public List<DictItem> getDictItemByTypeCode(HttpServletRequest request,@RequestParam String typeCode){
		//return iDictItemService.selectDictItemByTypeCode(typeCode);
		String px = Tools.convNull(request.getParameter("px"));
		return DictUtils.getDictList(typeCode,px);
	}
	
	@RequestMapping("/getDictType")
	@ResponseBody
	public List<DictType> getDictType(){
		return iDictTypeService.selectAll();
	}
	
	@RequestMapping("/upload/multFileUpload")
	public String multFileUpload(){
		return "/hygl/base/common/upload/multFileUpload";
	}
	
	@RequestMapping("/getImageCode") 
	public void getImageCode(HttpServletRequest request,HttpServletResponse response){
		try {
	        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
	        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expire", 0);
	        RandomValidateCode randomValidateCode = new RandomValidateCode();
	        
	        randomValidateCode.getRandcode(request, response);//输出图片方法

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getZiDianZHI.do") 
	public void getZiDianZHI(HttpServletRequest request,HttpServletResponse response){
		try {
	        String ZDID = Tools.convNull(request.getParameter("ZDID"));
	        String px = Tools.convNull(request.getParameter("px"));
	        String json = ZiDianUtils.getZiDianZHIToJson(ZDID,px); 
	        ComUtils.PrintWrite(response, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/exportExcel.do") 
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
		ExportExcel.Export(request, response);
	}
	
	@RequestMapping("/downloadFile.do") 
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
		try {
			String fileUrl = Tools.ConvNull(request.getParameter("fileUrl"));
			String fileName = Tools.ConvNull(request.getParameter("fileName"));
			fileName = URLDecoder.decode(fileName,"UTF-8");
			DownloadUtils.downloadFile(request, response, fileUrl, fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
/*	@RequestMapping("/exportExcel.do") 
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) {

		try {
			OutputStream out = response.getOutputStream();
			String fname = request.getParameter("filename");
			response.reset();// 清空输出流

			response.setCharacterEncoding("UTF-8");// 设置相应内容的编码格式
			fname = java.net.URLEncoder.encode(fname, "UTF-8");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(fname.getBytes("UTF-8"), "GBK") + ".xls");
			response.setContentType("application/ms-excel");// 定义输出类型
			String json = request.getParameter("columns");
			String json2 = request.getParameter("rows");
			ArrayList rows = (ArrayList) JSON.Decode(json);
			ArrayList datas = (ArrayList) JSON.Decode(json2);

			// 获得开始时间
			// long start = System.currentTimeMillis();
			// 创建Excel工作薄
			WritableWorkbook workbook = Workbook.createWorkbook(out);
			// 添加第一个工作表并设置第一个Sheet的名字
			WritableSheet sheet = workbook.createSheet("grid1", 0);
			Label label;
			// 写出列名
			for (int i = 0; i < rows.size(); i++) {
				HashMap hm = (HashMap) rows.get(i);
				Iterator iterator = hm.keySet().iterator();
				label = new Label(i, 0, hm.get("header").toString());
				sheet.addCell(label);
			}
			// 写出数据
			ArrayList list = datas;
			for (int i = 0; i < list.size(); i++) {
				HashMap hm1 = (HashMap) list.get(i);
				
				for (int j = 0; j < rows.size(); j++) {
					HashMap hm = (HashMap) rows.get(j);
					String key = hm.get("field").toString();
					String value = String.valueOf(hm1.get(key));
					label = new Label(j, i+1, value);
					sheet.addCell(label);
				}
				
			}

			// 写入数据
			workbook.write();
			// 关闭文件
			workbook.close();
			out.close();
			// long end = System.currentTimeMillis();
			// System.out.println("----完成该操作共用的时间是:"+(end-start)/1000);
		} catch (Exception e) {
			// System.out.println("---出现异常---");
			e.printStackTrace();
		}
	}*/
}
