package com.dt.cgq.mvc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.cgq.mvc.pojo.Dpzs;
import com.dt.cgq.mvc.pojo.Cgqsj;
import com.dt.cgq.mvc.service.DpzsService;

@Controller
@RequestMapping("/cgq/dpzs")
public class DpzsController {
	
	@Resource
	private DpzsService service;
	
	@RequestMapping("/findFx")
	@ResponseBody
	public Dpzs findFx(){
		Dpzs dpzs = new Dpzs();
		List<Cgqsj> list = service.findZxsj();
		String names[] = new String[list.size()];
		String value1s[] = new String[list.size()];
		String value2s[] = new String[list.size()];
		for(int i=0;i<list.size();i++) {
			names[i] = list.get(i).getSbbh();
			value1s[i] = list.get(i).getDqd();
			value2s[i] = list.get(i).getPjd();
		}
		dpzs.setNames(names);
		dpzs.setValue1s(value1s);
		dpzs.setValue2s(value2s);
		return dpzs;
	}
	
	@RequestMapping("/findFs")
	@ResponseBody
	public Dpzs findFs(){
		Dpzs dpzs = new Dpzs();
		List<Cgqsj> list = service.findZxsj();
		String names[] = new String[list.size()];
		String value1s[] = new String[list.size()];
		String value2s[] = new String[list.size()];
		for(int i=0;i<list.size();i++) {
			names[i] = list.get(i).getSbbh();
			value1s[i] = list.get(i).getDqz();
			value2s[i] = list.get(i).getPjz();
		}
		dpzs.setNames(names);
		dpzs.setValue1s(value1s);
		dpzs.setValue2s(value2s);
		return dpzs;
	}
	
	@RequestMapping("/findBzx")
	@ResponseBody
	public Dpzs findBzx(){
		Dpzs dpzs = new Dpzs();
		List<Dpzs> list = service.findYcsj("0");
		String names[] = new String[list.size()];
		String value1s[] = new String[list.size()];
		for(int i=0;i<list.size();i++) {
			names[i] = list.get(i).getName();
			value1s[i] = list.get(i).getCount();
		}
		dpzs.setNames(names);
		dpzs.setValue1s(value1s);
		return dpzs;
	}
	
	@RequestMapping("/findYc")
	@ResponseBody
	public Dpzs findYc(){
		Dpzs dpzs = new Dpzs();
		List<Dpzs> list = service.findYcsj("1");
		String names[] = new String[list.size()];
		String value1s[] = new String[list.size()];
		for(int i=0;i<list.size();i++) {
			names[i] = list.get(i).getName();
			value1s[i] = list.get(i).getCount();
		}
		dpzs.setNames(names);
		dpzs.setValue1s(value1s);
		return dpzs;
	}
	
}
