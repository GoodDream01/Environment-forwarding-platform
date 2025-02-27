package com.dt.zxhygl.mvc.base.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.zxhygl.mvc.base.pojo.Unit;
import com.dt.zxhygl.utils.UnitUtils;

@Controller
@RequestMapping("/base/unit")
public class UnitController {

	@RequestMapping("/getXsqList")
	@ResponseBody
	public List<Unit> getXsqList(){
		return UnitUtils.getUnitList_XSQ();
	}
	
	@RequestMapping("/getUnitList")
	@ResponseBody
	public List<Unit> getUnitList(){
		return UnitUtils.getUnitList();
	}
}
