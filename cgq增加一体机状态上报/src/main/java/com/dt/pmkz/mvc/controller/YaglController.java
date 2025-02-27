package com.dt.pmkz.mvc.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.common.interceptor.pagination.model.PageBounds;
import com.dt.common.utils.ComUtils;
import com.dt.common.utils.SystemConstant;
import com.dt.pmkz.mvc.pojo.Pm;
import com.dt.pmkz.mvc.pojo.Yagl;
import com.dt.pmkz.mvc.service.PmService;
import com.dt.pmkz.mvc.service.YaglService;
import com.dt.zxhygl.entity.AjaxJson;
import com.dt.zxhygl.entity.DataGridReturn;

@Controller
@RequestMapping("/pmkz/yagl")
public class YaglController {

	private static Logger logger = Logger.getLogger(YaglController.class);
	
	@Resource
	private YaglService service;
	@Resource
	private PmService pmservice;
	
	@RequestMapping("/listUi")
	public String listUi(){
		return "/pmkz/yagl/list";
	}
	@RequestMapping("/addUi")
	public String addUi(){
		return "/pmkz/yagl/add";
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public DataGridReturn getList(HttpServletRequest request){
		logger.info("查询短信记录列表....");
		DataGridReturn grid = null;
		try {
			String pageNo = ComUtils.ConvNull(request.getParameter("pageIndex"), "0");
			String pageSize = ComUtils.ConvNull(request.getParameter("pageSize"), String.valueOf(SystemConstant.pageSize));
			boolean isLimit=Boolean.parseBoolean(ComUtils.ConvNull(request.getParameter("isLimit"),"true"));
			PageBounds pageBounds = new PageBounds(Integer.parseInt(pageNo)+1, Integer.parseInt(pageSize), isLimit);
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("yadj", ComUtils.ConvNull(request.getParameter("yadj")));
			pageBounds.setParamMap(paramMap);
			List<Yagl> list = service.findByPage(pageBounds);
			pageBounds.setList(list);
			grid = new DataGridReturn(pageBounds.getTotalCount(), list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询短信记录出错....");
			logger.error(e.getMessage());
		}
		return grid;
	}
	
	@RequestMapping("/getById")
	@ResponseBody
	public Yagl getById(String id){
		logger.info("查询信息....id="+id+"");
		Yagl t = null;
		try {
			t = service.selectByPrimaryKey(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("查询信息失败....id="+id+"");
			logger.error(e.getMessage());
		}
		return t;
	}
	
	@RequestMapping("save")
	@ResponseBody
	public AjaxJson save(@RequestBody Yagl t){
		logger.info("保存信息....");
		boolean success = false;
		try {	
			Yagl yagl = service.selectByYaPm(t.getYadj(), t.getPmid());
			if(yagl!=null) {
				success = service.update(t);
			}else {
				success = service.insert(t);
			}
			Pm pm = pmservice.selectByPrimaryKey(t.getPmid());
			Socket socket = new Socket(pm.getIp(),pm.getDk());
			String str = "";
			
			if(t.getUpload()!=null && !t.getUpload().equals("")) {
				String imagename = t.getYadj()+".bmp";
				BufferedImage image = ImageIO.read(new File(t.getUpload()));
				ImageIO.write(image, "BMP", new File("C:\\Users\\Administrator\\Desktop",imagename));
				String datastr = "30303130"+TCPClient.stringToAscii(imagename).replaceAll(" ", "")+"2b";
				byte[] ss = TCPClient.fileConvertToByteArray(new File("C:\\Users\\Administrator\\Desktop",imagename));
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
			
			String filename = "play"+t.getYadj()+".lst";
			String datastr = pm.getDz()+"3130"+TCPClient.stringToAscii(filename)+"2b00000000";
			if(t.getUpload()!=null && !t.getUpload().equals("")) {
				str = "[playlist]\r\n" + 
						"item_no = 1\r\n" + 
						"item0 = "+t.getTlsj()+", "+t.getCzfs()+", "+t.getTxsd()+", "+"\\B"+t.getYadj()
								+ "\\C000000\\fs"+t.getZtkg()+"\\c"+t.getZtys()+t.getWznr();
			}else {
				str = "[playlist]\r\n" + 
						"item_no = 1\r\n" + 
						"item0 = "+t.getTlsj()+", "+t.getCzfs()+", "+t.getTxsd()+", "
								+ "\\C000000\\fs"+t.getZtkg()+"\\c"+t.getZtys()+t.getWznr();
			}
			TCPClient.writeData(str,filename);
			byte[] ss = TCPClient.fileConvertToByteArray(new File("C:\\Users\\Administrator\\Desktop",filename));
			datastr = (datastr+TCPClient.BinaryToHexString(ss)).replaceAll(" ", "");
			TCPClient.socketclient(socket,datastr);
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
	
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxJson delete(String id){
		logger.info("删除信息....id="+id+"");
		boolean success = false;
		try {
			success = service.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("删除信息失败....id="+id+"");
			logger.error(e.getMessage());
		}
		String msg = "删除失败！";
		if(success){
			msg = "删除成功！";
		}
		AjaxJson ajaxJson = new AjaxJson(success, msg);
		return ajaxJson;
	}

	@RequestMapping("/qdya")
	@ResponseBody
	public AjaxJson qdya(String yadj){
		boolean success = true;
		try {
			List<Yagl> list = service.selectByYa(yadj);
			for(int i=0;i<list.size();i++) {
				Pm pm = pmservice.selectByPrimaryKey(list.get(i).getPmid());
				Socket socket = new Socket(pm.getIp(),pm.getDk());
				String datastr = pm.getDz()+"3938"+TCPClient.stringToAscii(yadj);
				datastr = datastr.replaceAll(" ", "");
				TCPClient.socketclient(socket,datastr);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		String msg = "删除失败！";
		if(success){
			msg = "删除成功！";
		}
		AjaxJson ajaxJson = new AjaxJson(success, msg);
		return ajaxJson;
	}
	
}
