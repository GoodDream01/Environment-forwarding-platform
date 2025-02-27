package com.dt.sjcs.mvc.controller;

import com.dt.common.utils.ComUtils;
import com.dt.sjcs.mvc.pojo.Sjtj;
import com.dt.sjcs.mvc.service.SjtjService;
import com.dt.sjcs.mvc.service.UrlpzService;
import com.dt.zxhygl.mvc.base.pojo.User;
import com.dt.zxhygl.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sjcs/sjqx")
public class SjqxController {
	
	@Resource
	private SjtjService service;
	@Resource
	private UrlpzService urlpzservice;
	
	@RequestMapping("/listUi")
	public String listUi(){
		return "/sjcs/sjqx/list";
	}

	@RequestMapping("/getSb")
	@ResponseBody
	public List<Sjtj> getSb(){
		List<Sjtj> list = new ArrayList<>();
		User currentUser = UserUtils.getCurrentUser();
		if(!currentUser.getRole().equals("0")){
			String sbbh[] = urlpzservice.findSbbh(currentUser.getQyid()).split(",");
			for (int i = 0; i < sbbh.length; i++) {
				Sjtj t = new Sjtj();
				t.setName(sbbh[i]);
				list.add(t);
			}
		}else{
			list = service.findSbbh();
		}
		return list;
	}

	@RequestMapping("/getTj")
	@ResponseBody
	public Sjtj getTj(HttpServletRequest request){
		Sjtj t = new Sjtj();
		List<Sjtj> list = new ArrayList<>();
		String sbid = ComUtils.ConvNull(request.getParameter("sbid"));
		String kssj = ComUtils.ConvNull(request.getParameter("kssj"));
		String jssj = ComUtils.ConvNull(request.getParameter("jssj"));
		int count = findSjcz(kssj,jssj);
		if(count>=2){
			list = service.TjByDay(sbid,kssj,jssj);
		}else{
			list = service.TjByHour(sbid,kssj,jssj);
		}
		if(list.size()>0){
			String names[] = new String[list.size()];
			String values1[] = new String[list.size()];
			String values2[] = new String[list.size()];
			String values3[] = new String[list.size()];
			String values4[] = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				names[i] = list.get(i).getName();
				values1[i] = list.get(i).getValue1();
				values2[i] = list.get(i).getValue2();
				values3[i] = list.get(i).getValue3();
				values4[i] = list.get(i).getValue4();
			}
			t.setNames(names);
			t.setValues1(values1);
			t.setValues2(values2);
			t.setValues3(values3);
			t.setValues4(values4);
		}
		return t;
	}

	public int findSjcz(String start,String end) {
		DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		int days = 0;
		try {
			Date star = dft.parse(start);//开始时间
			Date endDay = dft.parse(end);//结束时间
			Date date = new Date();
			if(endDay.getTime()>date.getTime()) endDay = date;
			Date nextDay = star;
			while(nextDay.before(endDay)){//当明天不在结束时间之前是终止循环
				Calendar cld = Calendar.getInstance();
				cld.setTime(star);
				cld.add(Calendar.DATE, 1);
				star = cld.getTime();
				//获得下一天日期字符串
				nextDay = star;
				days++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days+1;
	}

}
