package com.dt.zxhygl.mvc.base.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.common.utils.ComUtils;
import com.dt.zxhygl.mvc.base.entity.DemoBarChartData;
import com.dt.zxhygl.mvc.base.service.IDemoBarChartService;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.series.Bar;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/ueditor")
	public String ueditor(){
		return "/hygl//base/demo/ueditor/ueditor";
	}
	
	@RequestMapping("/upload1")
	public String upload(){
		return "/hygl//base/demo/upload/upload1";
	}
	
	@RequestMapping("/barChart1Ui")
	public String barChart1Ui(){
		return "/hygl//base/demo/echart/bar1";
	}
	/*********************************************/
	@Resource
	private IDemoBarChartService iDemoBarChartService;
	
	@RequestMapping("/getBarChart1")
	public DemoBarChartData getBarChart1(HttpServletRequest request){
		//创建数据对象
		DemoBarChartData result = new DemoBarChartData();
		
		//接收参数
		String zyear = ComUtils.ConvNull(request.getParameter("zyear"));
		
		//创建图表参数对象
		Option option = new Option();
		//创建类目轴
	    CategoryAxis category = new CategoryAxis();
	    //创建图例
	    Legend legend = new Legend();
	    
	    Bar bar = new Bar("会议次数");
		
	    for(int i=1;i<=12;i++){
	    	String month = String.valueOf(i);
	    	
	    	int count = iDemoBarChartService.getHyCountByMonth(zyear, month);
	    	
	    	category.data(month+"月");
	    	
	    	bar.data(count);
	    	
	    	
	    }
		//设置图例
		//option.legend(legend);
		//设置类目轴
	    option.xAxis(category);
	    //设置数据
	    option.series(bar);
	    
		result.setOption(option);
		
		return result;
	}
	
	@RequestMapping("/videoUi")
	public String videoUi(){
		return "/hygl//base/demo/video/video";
	}
}
