package com.dt.cgq.mvc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.cgq.mvc.pojo.Dpzs;
import com.dt.cgq.mvc.pojo.Sbgl;
import com.dt.cgq.mvc.service.DpzsService;
import com.dt.cgq.mvc.service.SbglService;
import com.dt.zxhygl.mvc.base.pojo.User;
import com.dt.zxhygl.utils.UserUtils;

@Controller
@RequestMapping("/cgq/sjtj")
public class SjtjController {
    //按远程设备点位以时间维度对风力数据进行分析，每月1日自主执行数据分析指令，生成并展示风力分析曲线图。---曲线图横轴为时间（单位-天），纵轴为风力数据（单位-级）
	
	@Resource
	private DpzsService dpzsservice;
	@Resource
	private SbglService sbglservice;
	
	@RequestMapping("/listUi")
	public String listUi(){
		return "/cgq/sjtj/list";
	}
	
	/*
	 * @RequestMapping("/findFlxx")
	 * 
	 * @ResponseBody public List<Dpzs> findFlxx(){ List<Dpzs> list = new
	 * ArrayList<>();
	 * 
	 * Map<String,String> paramMap = new HashMap<String,String>(); User currentUser
	 * = UserUtils.getCurrentUser(); paramMap.put("qyid", currentUser.getQyid());
	 * List<Sbgl> sbgls = sbglservice.selectAll(paramMap);
	 * 
	 * Calendar calendar = Calendar.getInstance(); calendar.add(Calendar.MONTH, -1);
	 * calendar.set(Calendar.DAY_OF_MONTH, 1); SimpleDateFormat sdf = new
	 * SimpleDateFormat("MM.dd"); int count =
	 * calendar.getActualMaximum(Calendar.DAY_OF_MONTH); String names[] = new
	 * String[count]; for(int i=0;i<count;i++) { names[i] =
	 * sdf.format(calendar.getTime()); calendar.add(Calendar.DATE, 1); }
	 * 
	 * for(int i=0;i<sbgls.size();i++) { Dpzs dpzs = new Dpzs();
	 * dpzs.setName(sbgls.get(i).getSbbh()); dpzs.setType("line");
	 * dpzs.setData(dpzsservice.findFlsj(sbgls.get(i).getSbbh()));
	 * dpzs.setNames(names); list.add(dpzs); } return list; }
	 */
	
	@RequestMapping("/findFlxx")
	@ResponseBody
	public Dpzs findFlxx(String sbbh,String kssj,String jssj){
		Dpzs dpzs = new Dpzs();
		List<Dpzs> list = dpzsservice.findFlsj(sbbh,kssj,jssj);
		if(list.size()>0) {
			String names[] = new String[list.size()];
			String data[] = new String[list.size()];
			for(int i=0;i<list.size();i++) {
				names[i] = list.get(i).getName();
				data[i] = list.get(i).getCount();
			}
			dpzs.setNames(names);
			dpzs.setData(data);
		}else {
			dpzs.setNames(new String[]{"无"});
			dpzs.setData(new String[]{"0"});
		}
		return dpzs;
	}
	
}
