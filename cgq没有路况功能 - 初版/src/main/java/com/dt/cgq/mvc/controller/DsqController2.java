package com.dt.cgq.mvc.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.cgq.mvc.pojo.Cgqsj;
import com.dt.cgq.mvc.pojo.Cgqyc;
import com.dt.cgq.mvc.pojo.Dxjl;
import com.dt.cgq.mvc.pojo.Rygl;
import com.dt.cgq.mvc.pojo.Sbgl;
import com.dt.cgq.mvc.pojo.Wx;
import com.dt.cgq.mvc.pojo.Ycsj;
import com.dt.cgq.mvc.service.CgqsjService;
import com.dt.cgq.mvc.service.DsqService;
import com.dt.cgq.mvc.service.DxjlService;
import com.dt.cgq.mvc.service.SbglService;

@EnableScheduling
@Component
@Controller
@RequestMapping("/cgq/dsq")
public class DsqController2 {

	//TcpServer tcpServer;
	
	@Resource
	private DsqService dsqservice;
	@Resource
	private SbglService sbglservice;
	@Resource
	private DxjlService dxjlservice;
	@Resource
	private CgqsjService cgqsjservice;
	
	@Scheduled(cron = "0 02 0 * * ?")//每天0:02执行
	public void insert() throws Exception {
		Cgqsj t = new Cgqsj();
		cgqsjservice.insert(t);
	}
	
	@Scheduled(cron = "0 02 1 1 * ?")//每月1号凌晨1点02执行一次
	public void delete() throws Exception {
		String id = "";
		cgqsjservice.delete(id);
	}
	
	//@RequestMapping("/scsj")
	//@Scheduled(cron = "0 */5 * * * ?")
	public void scsj() throws Exception {
		dsqservice.delete();
	}
	
	@RequestMapping("/tcp1")
	public void tcp1() throws Exception {
		//创建服务器套接字，绑定到指定的端口
	    //ServerSocket serverSocket = new ServerSocket(9027);
	    //tcpServer = new TcpServer(serverSocket);
	}
	
	@RequestMapping("/tcp2")
	public void tcp2(String devid) throws Exception {
        //new Thread(()-> tcpServer.start(devid)).start();
	}
		
	@RequestMapping("/bzxsj")
	//@Scheduled(cron = "0 */30 * * * ?")
	public void bzxsj() throws Exception {
		Map<String,String> paramMap = new HashMap<String,String>();
		List<Sbgl> sbgl = sbglservice.selectAll(paramMap);
		for(int i=0;i<sbgl.size();i++) {
			List<Cgqsj> cgqsj = dsqservice.find30Sj(sbgl.get(i).getSbbh());
			if(cgqsj.isEmpty()) {
				if(sbgl.get(i).getSffs().equals("0")) {//未发送信息又出现传感器未在线
					Cgqyc cgqyc = new Cgqyc();
					cgqyc.setSbbh(sbgl.get(i).getSbbh());
					cgqyc.setState("0");//0不在线
					cgqyc.setCount(1);
					dsqservice.insert(cgqyc);
					sbgl.get(i).setSffs("1");
					dsqservice.update(sbgl.get(i));
					
					List<Rygl> rygl = dsqservice.findPhone(sbgl.get(i).getFzr());
					String phone = "",xm = "";
					for(int n=0;n<rygl.size();n++) {
						if(n==rygl.size()-1) {
							phone = phone + rygl.get(n).getPhone();
							xm = xm + rygl.get(n).getName();
						}else {
							phone = phone + rygl.get(n).getPhone()+",";
							xm = xm + rygl.get(n).getName()+",";
						}
					}
					String content = "【传感器设备"+sbgl.get(i).getSbbh()+"】您负责的该传感器未在线，请及时查看维修";
					String result = requestOCRForHttp(content,phone);
					JSONObject json = JSONObject.parseObject(result);
				    String list = json.getString("list");
				    JSONArray arr = JSONArray.parseArray(list);
				    JSONObject jsonObject = arr.getJSONObject(arr.size()-1);
			        String pic = jsonObject.getString("result");
					Dxjl dxjl = new Dxjl();
					dxjl.setSbbh(sbgl.get(i).getSbbh());
					dxjl.setFsrxm(xm);
					dxjl.setFsrdh(phone);
					dxjl.setYcyy(content);
					if(pic.equals("10")) dxjl.setFszt("原发号码错误");
					if(pic.equals("15")) dxjl.setFszt("余额不足");
					if(pic.equals("17")) dxjl.setFszt("账号签名无效");
					else dxjl.setFszt("发送成功");
					dxjlservice.insert(dxjl);
				}
			}else {
				sbgl.get(i).setSffs("0");
				dsqservice.update(sbgl.get(i));
			}
		}
	}

	@RequestMapping("/ycsj")
	@Scheduled(cron = "0 */15 * * * ?")
	public void ycsj() throws Exception {
		Map<String,String> paramMap = new HashMap<String,String>();
		List<Sbgl> sbgl = sbglservice.selectAll(paramMap);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i=0;i<sbgl.size();i++) {
			if(sbgl.get(i).getSffs1().equals("0")) {//未发送异常短信的情况下查询连续两次到达6级风速
				List<Cgqsj> cgqsj = dsqservice.findycSj(sbgl.get(i).getSbbh());
				if(!cgqsj.isEmpty() && cgqsj.size()>=4) {
					//String fsxx = sdf.format(cgqsj.get(3).getTime())+"当前风速为"+cgqsj.get(3).getDqz()+"m/s,风力等级"+getFLDJ(cgqsj.get(3).getDqz())+"级，请注意监测。";
					Cgqyc cgqyc = new Cgqyc();
					cgqyc.setSbbh(sbgl.get(i).getSbbh());
					cgqyc.setState("1");//1异常
					cgqyc.setCount(1);
					dsqservice.insert(cgqyc);
					sbgl.get(i).setSffs1("1");
					dsqservice.update1(sbgl.get(i));
					
					Dxjl dxjl = new Dxjl();
					dxjl.setSbbh(sbgl.get(i).getSbbh());
					List<Rygl> rygl = dsqservice.findPhone(sbgl.get(i).getFzr());
					String phone = "",xm = "";
					for(int n=0;n<rygl.size();n++) {
						if(n==rygl.size()-1) {
							phone = phone + rygl.get(n).getPhone();
							xm = xm + rygl.get(n).getName();
						}else {
							phone = phone + rygl.get(n).getPhone()+",";
							xm = xm + rygl.get(n).getName()+",";
						}
					}
					dxjl.setFsrxm(xm);
					dxjl.setFsrdh(phone);
					
					//发送微信公众号信息
					if(sbgl.get(i).getFz()!=null && !sbgl.get(i).getFz().equals("")) {
						Wx wx = new Wx();
						wx.setSbbh("ID"+sbgl.get(i).getSbbh());
						String sbmcarr[] = sbgl.get(i).getMc().split("-");
						String sbmc = sbmcarr[1]+sbmcarr[0];
						if(sbmc.length()>20) {
							wx.setMc(sbmc.substring(0, 17)+"...");
						}else {
							wx.setMc(sbmc);
						}
						if(sbgl.get(i).getDz().length()>20) {
							wx.setDz(sbgl.get(i).getDz().substring(0, 17)+"...");
						}else {
							wx.setDz(sbgl.get(i).getDz());
						}
						for(int n=0;n<cgqsj.size();n++) {
							if(Integer.valueOf(cgqsj.get(n).getFldj())>=6) {
								wx.setTime(sdf.format(cgqsj.get(n).getTime()));
								wx.setXx("风力"+cgqsj.get(n).getFldj()+"级");
							}
						}
						wx.setJd(sbgl.get(i).getJd());
						wx.setWd(sbgl.get(i).getWd());
						TimeUnit.SECONDS.sleep(300);
					    List<Ycsj> list = dsqservice.findSpxx(sbgl.get(i).getSbbh());
			            String splj = "/uploadfiles/files/111.mp4";
			            if(list.size()>0){
			        		splj = list.get(0).getSplj();
			        	}
			            wx.setSplj(splj);
						String [] sbfzs = sbgl.get(i).getFz().split(",");
						for (String sbfz: sbfzs) {//全部信息分组发送，多个分组发送多次
							wx.setBh(Integer.parseInt(sbfz));
							GzhtsController.recursionOpenId("",wx);
						}
						dxjl.setFszt("0");
						dxjl.setYcyy(wx.getSbbh()+"-"+wx.getMc()+"-"+wx.getDz()+"-"+wx.getTime()+"-"+wx.getXx());
						dxjlservice.insert(dxjl);
					}
					
					//发送短信
					//String content = "【传感器设备"+sbgl.get(i).getSbbh()+"】赞皇县"+sbgl.get(i).getMc()+","+sbgl.get(i).getDz()+"。风速信息"+fsxx;
					//dxjl.setYcyy(content);
					//String result = requestOCRForHttp(content,phone);
					//JSONObject json = JSONObject.parseObject(result);
				    //String list = json.getString("list");
				    //JSONArray arr = JSONArray.parseArray(list);
				    //JSONObject jsonObject = arr.getJSONObject(arr.size()-1);
			        //String pic = jsonObject.getString("result");
					//if(pic.equals("10")) dxjl.setFszt("原发号码错误");
					//if(pic.equals("15")) dxjl.setFszt("余额不足");
					//if(pic.equals("17")) dxjl.setFszt("账号签名无效");
					//else dxjl.setFszt("发送成功");
					//dxjlservice.insert(dxjl);
				}
			}else {//查询是否连续5次低于6级风力的情况
				List<Cgqsj> cgqsj = dsqservice.findzcSj(sbgl.get(i).getSbbh());
				if(!cgqsj.isEmpty() && cgqsj.size()>=10) {
					sbgl.get(i).setSffs1("0");
					dsqservice.update1(sbgl.get(i));
					
					Dxjl dxjl = new Dxjl();
					dxjl.setSbbh(sbgl.get(i).getSbbh());
					List<Rygl> rygl = dsqservice.findPhone(sbgl.get(i).getFzr());
					String phone = "",xm = "";
					for(int n=0;n<rygl.size();n++) {
						if(n==rygl.size()-1) {
							phone = phone + rygl.get(n).getPhone();
							xm = xm + rygl.get(n).getName();
						}else {
							phone = phone + rygl.get(n).getPhone()+",";
							xm = xm + rygl.get(n).getName()+",";
						}
					}
					dxjl.setFsrxm(xm);
					dxjl.setFsrdh(phone);
					
					//发送微信公众号信息
					if(sbgl.get(i).getFz()!=null && !sbgl.get(i).getFz().equals("")) {
						Wx wx = new Wx();
						wx.setSbbh("ID"+sbgl.get(i).getSbbh());
						String sbmcarr[] = sbgl.get(i).getMc().split("-");
						String sbmc = sbmcarr[1]+sbmcarr[0];
						if(sbmc.length()>20) {
							wx.setMc(sbmc.substring(0, 17)+"...");
						}else {
							wx.setMc(sbmc);
						}
						if(sbgl.get(i).getDz().length()>20) {
							wx.setDz(sbgl.get(i).getDz().substring(0, 17)+"...");
						}else {
							wx.setDz(sbgl.get(i).getDz());
						}
						wx.setTime(sdf.format(cgqsj.get(9).getTime()));
						wx.setXx("告警解除");
						wx.setJd(sbgl.get(i).getJd());
						wx.setWd(sbgl.get(i).getWd());
						TimeUnit.SECONDS.sleep(300);
						List<Ycsj> list = dsqservice.findSpxx(sbgl.get(i).getSbbh());
			            String splj = "/uploadfiles/files/111.mp4";
			            if(list.size()>0){
			        		splj = list.get(0).getSplj();
			        	}
			            wx.setSplj(splj);
						String [] sbfzs = sbgl.get(i).getFz().split(",");
						for (String sbfz: sbfzs) {//全部信息分组发送，多个分组发送多次
							wx.setBh(Integer.parseInt(sbfz));
							GzhtsController.recursionOpenId("",wx);
						}
						dxjl.setFszt("0");
						dxjl.setYcyy(wx.getSbbh()+"-"+wx.getMc()+"-"+wx.getDz()+"-"+wx.getTime()+"-"+wx.getXx());
						dxjlservice.insert(dxjl);
					}
					
					//发送短信
					//String content = "【传感器设备"+sbgl.get(i).getSbbh()+"】"+sbgl.get(i).getMc()+","+sbgl.get(i).getDz()+"。风速已回到5级以下";
					//dxjl.setYcyy(content);
					//String result = requestOCRForHttp(content,phone);
					//JSONObject json = JSONObject.parseObject(result);
				    //String list = json.getString("list");
				    //JSONArray arr = JSONArray.parseArray(list);
				    //JSONObject jsonObject = arr.getJSONObject(arr.size()-1);
			        //String pic = jsonObject.getString("result");
					//if(pic.equals("10")) dxjl.setFszt("原发号码错误");
					//if(pic.equals("15")) dxjl.setFszt("余额不足");
					//if(pic.equals("17")) dxjl.setFszt("账号签名无效");
					//else dxjl.setFszt("发送成功");
					//dxjlservice.insert(dxjl);
				}
			}
		}
	}
	
	public String getFLDJ(String str) {
		double fl = Double.parseDouble(str);
		if(fl>=0.0&&fl<=0.2){
			return "0";
		}else if(fl>=0.3&&fl<=1.5){
			return "1";
		}else if(fl>=1.6&&fl<=3.3){
			return "2";
		}else if(fl>=3.4&&fl<=5.4){
			return "3";
		}else if(fl>=5.5&&fl<=7.9){
			return "4";
		}else if(fl>=8.0&&fl<=10.7){
			return "5";
		}else if(fl>=10.8&&fl<=13.8){
			return "6";
		}else if(fl>=13.9&&fl<=17.1){
			return "7";
		}else if(fl>=17.2&&fl<=20.7){
			return "8";
		}else if(fl>=20.8&&fl<=24.4){
			return "9";
		}else if(fl>=24.5&&fl<=28.4){
			return "10";
		}else if(fl>28.5&&fl<=32.6){
			return "11";
		}else if(fl>32.7&&fl<=36.9){
			return "12";
		}else{
			return "12";
		}
    }
	
	public String requestOCRForHttp(String content,String phone) throws Exception {
		URL u = new URL("http://218.12.79.235:7862/sms");	
		//此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类的子类HttpURLConnection
		HttpURLConnection conn = (HttpURLConnection)u.openConnection();				
		conn.setRequestMethod("POST");		//请求方式		
		conn.setDoOutput(true);		//设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true, 默认是false;   		
		conn.setDoInput(true);		//设置是否从httpUrlConnection读入，默认情况下是true;		
		conn.setAllowUserInteraction(false);		//allowUserInteraction如果为true，则在允许用户交互（例如弹出一个验证对话框）的上下文中对此URL进行检查。		
		PrintStream ps = new PrintStream(conn.getOutputStream());
		String param = "action=send&account=935001&password=cDKy8E&mobile="+phone+"&content="+content+"&extno=106903&rt=json";
		ps.print(param);				
		BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));				
		String line,resultStr="";				
		while(null != (line=bReader.readLine())){
			resultStr +=line;
		}        
		System.out.println(resultStr);        
		bReader.close();		//创建流进行写入或读取返回值，创建用完后记得关闭流          
		return resultStr;
    }  
	
}
