package com.dt.sjcs.mvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.dt.sjcs.mvc.pojo.Sjpz;
import com.dt.sjcs.mvc.service.SjpzService;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.dt.sjcs.mvc.pojo.Urlpz;
import com.dt.sjcs.mvc.service.UrlpzService;
import org.springframework.web.bind.annotation.ResponseBody;

@EnableScheduling
@Component
@Controller
@RequestMapping("/cgq/sjcs")
public class SjcsTask {
	
	@Resource
	private UrlpzService urlpzservice;
	@Resource
	private SjpzService sjpzservice;
	
	@RequestMapping("/ztlj")
	@ResponseBody
	@Scheduled(cron = "0 */1 * * * ?")
	public void sjcspost() throws Exception {
		List<Urlpz> urlpzlist = urlpzservice.findList();
		for(int i=0;i<urlpzlist.size();i++) {
			JSONObject jsonObject = new JSONObject();
			if(urlpzlist.get(i).getBj().equals("1")){
				JSONObject jsonObject1 = new JSONObject();
				jsonObject1.put("factoryCode", urlpzlist.get(i).getFactoryCode());
				jsonObject1.put("deviceClass", urlpzlist.get(i).getDeviceClass());
				jsonObject1.put("appId", urlpzlist.get(i).getAppId());
				jsonObject1.put("secretKey", urlpzlist.get(i).getSecretKey());
				jsonObject.put("verify", jsonObject1);
				List<Sjpz> cgqsjlist = sjpzservice.findList(urlpzlist.get(i).getSbbh());
				String zdbm = urlpzlist.get(i).getZdbm();
				for(int j=0;j<cgqsjlist.size();j++) {
					JSONObject jsonObject2 = new JSONObject();
					jsonObject2.put("equipmentId", cgqsjlist.get(j).getSbid());
					jsonObject2.put("timeStamp", cgqsjlist.get(j).getDate().getTime()/1000);//日期
					jsonObject2.put("distance", 5);
					if(zdbm.indexOf("sunVolt")>-1){
						jsonObject2.put("sunVolt", cgqsjlist.get(j).getDy());//太阳能充电电压
					}
					if(zdbm.indexOf("power")>-1){
						double sunVolt = Double.parseDouble(cgqsjlist.get(j).getDy());
						int power = Integer.parseInt(new java.text.DecimalFormat("0").format(1+(sunVolt-9.6)/1*33));
						jsonObject2.put("power", power);//电量
					}
					if(zdbm.indexOf("temperature")>-1){
						jsonObject2.put("temperature", cgqsjlist.get(j).getDqwdz());//空气温度
					}
					if(zdbm.indexOf("humidity")>-1){
						jsonObject2.put("humidity", cgqsjlist.get(j).getDqsdz());//空气湿度
					}
					if(zdbm.indexOf("pressure")>-1){
						jsonObject2.put("pressure", cgqsjlist.get(j).getDqylz());//大气压力
					}
					if(zdbm.indexOf("windSpeed")>-1){
						jsonObject2.put("windSpeed", cgqsjlist.get(j).getFsssz());//风速
					}
					if(zdbm.indexOf("windDirection")>-1){
						jsonObject2.put("windDirection", cgqsjlist.get(j).getFxssz());//风向
					}
					if(zdbm.indexOf("rainfall")>-1){
						//double rainfall = Double.parseDouble(cgqsjlist.get(j).getDqjyz());
						//jsonObject2.put("rainfall", Double.toString(rainfall*60));//降雨量
						jsonObject2.put("rainfall","0.0");//降雨量
					}
					if(zdbm.indexOf("cumulativerainfall")>-1){
						List<Sjpz> JyList = sjpzservice.findJyList(cgqsjlist.get(j).getSbid(),cgqsjlist.get(j).getDate());
						if(JyList.size()>0){
							double rainfall = (Double.parseDouble(cgqsjlist.get(j).getDqjyz())-Double.parseDouble(JyList.get(0).getDqjyz()))*12;
							if(rainfall>0){
								jsonObject2.put("cumulativerainfall", Double.toString(rainfall));//雨量累计
							}else{
								jsonObject2.put("cumulativerainfall", "0.0");//雨量累计
							}
						}else{
							jsonObject2.put("cumulativerainfall", "0.0");//雨量累计
						}
					}
					if(zdbm.indexOf("solarRadiation")>-1){
						jsonObject2.put("solarRadiation", cgqsjlist.get(j).getZfsz());//太阳辐射
					}
					jsonObject.put("dataInfo", jsonObject2);
					System.out.println(jsonObject.toJSONString());
					String responseContent = sendHttpPost(urlpzlist.get(i).getUrl(), jsonObject.toJSONString());
					cgqsjlist.get(j).setTsdw(urlpzlist.get(i).getDwmc());
					JSONObject object = JSONObject.parseObject(responseContent);
					cgqsjlist.get(j).setTsjg(object.getString(urlpzlist.get(i).getMsg()));
					sjpzservice.update(cgqsjlist.get(j));
				}
			}else if(urlpzlist.get(i).getBj().equals("2")){
				String sbbh[] = urlpzlist.get(i).getSbbh().split(",");
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
				for(int j=0;j<sbbh.length;j++){
					List<Sjpz> cgqsjlist = sjpzservice.findList(sbbh[j]);
					if(cgqsjlist.size()>0){
						jsonObject.put("producer", "FAT");
						jsonObject.put("appId", urlpzlist.get(i).getAppId());
						long timestamp = new Date().getTime();
						jsonObject.put("timestamp", timestamp);
						jsonObject.put("equipmentCode", sbbh[j]);
						String requestId = UUID.randomUUID().toString();
						jsonObject.put("requestId", requestId);
						String sign = urlpzlist.get(i).getAppId()+timestamp+sbbh[j]+requestId+urlpzlist.get(i).getSecretKey();
						jsonObject.put("sign", DigestUtils.md5DigestAsHex(sign.getBytes()).toUpperCase());
						String zdbm = urlpzlist.get(i).getZdbm();
						JSONArray array = new JSONArray();
						for (int k = 0; k < cgqsjlist.size(); k++) {
							JSONObject jsonObject1 = new JSONObject();
							jsonObject1.put("collectTime",sdf.format(cgqsjlist.get(k).getDate()));
							jsonObject1.put("distance", 5);
							if(zdbm.indexOf("sunVolt")>-1){
								jsonObject1.put("sunVolt", Double.valueOf(cgqsjlist.get(k).getDy()));//太阳能充电电压
							}
							if(zdbm.indexOf("power")>-1){
								double sunVolt = Double.parseDouble(cgqsjlist.get(k).getDy());
								int power = Integer.parseInt(new java.text.DecimalFormat("0").format(1+(sunVolt-9.6)/1*33));
								jsonObject1.put("power", power);//电量
							}
							if(zdbm.indexOf("temperature")>-1){
								jsonObject1.put("temperature", cgqsjlist.get(k).getDqwdz());//空气温度
							}
							if(zdbm.indexOf("humidity")>-1){
								jsonObject1.put("humidity", cgqsjlist.get(k).getDqsdz());//空气湿度
							}
							if(zdbm.indexOf("pressure")>-1){
								jsonObject1.put("pressure", cgqsjlist.get(k).getDqylz());//大气压力
							}
							if(zdbm.indexOf("windSpeed")>-1){
								jsonObject1.put("windSpeed", cgqsjlist.get(k).getFsssz());//风速
							}
							if(zdbm.indexOf("windDirection")>-1){
								jsonObject1.put("windDirection", cgqsjlist.get(k).getFxssz());//风向
							}
							if(zdbm.indexOf("rainfall")>-1){
								//double rainfall = Double.parseDouble(cgqsjlist.get(k).getDqjyz());
								//jsonObject1.put("rainfall", Double.toString(rainfall*60));//降雨量
								jsonObject1.put("rainfall", "0.0");//降雨量
							}
							if(zdbm.indexOf("cumulativerainfall")>-1){
								List<Sjpz> JyList = sjpzservice.findJyList(cgqsjlist.get(k).getSbid(),cgqsjlist.get(k).getDate());
								if(JyList.size()>0){
									double rainfall = (Double.parseDouble(cgqsjlist.get(k).getDqjyz())-Double.parseDouble(JyList.get(0).getDqjyz()))*12;
									if(rainfall>0){
										jsonObject1.put("cumulativerainfall", Double.toString(rainfall));//雨量累计
									}else{
										jsonObject1.put("cumulativerainfall", "0.0");//雨量累计
									}
								}else{
									jsonObject1.put("cumulativerainfall", "0.0");//雨量累计
								}
							}
							if(zdbm.indexOf("solarRadiation")>-1){
								jsonObject1.put("solarRadiation", cgqsjlist.get(k).getZfsz());//太阳辐射
							}
							array.add(jsonObject1);
						}
						jsonObject.put("uploadData",array);
						System.out.println(jsonObject.toJSONString());
						String responseContent = sendHttpPost(urlpzlist.get(i).getUrl(), jsonObject.toJSONString());
						for (int k = 0; k < cgqsjlist.size(); k++){
							cgqsjlist.get(k).setTsdw(urlpzlist.get(i).getDwmc());
							JSONObject object = JSONObject.parseObject(responseContent);
							cgqsjlist.get(k).setTsjg(object.getString(urlpzlist.get(i).getMsg()));
							sjpzservice.update(cgqsjlist.get(k));
						}
					}
				}
			}
		}
	}

	public static String sendHttpPost(String url, String JSONBody) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000).setSocketTimeout(5000).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(JSONBody));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String responseContent = EntityUtils.toString(entity, "UTF-8");
		System.out.println(responseContent);
        response.close();
        httpClient.close();
        return responseContent;
    }

}
