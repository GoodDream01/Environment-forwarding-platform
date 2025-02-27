package com.dt.cgq.mvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.dt.cgq.mvc.pojo.Wx;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GzhtsController {

    public static void main(String[] args) throws Exception {
    	Wx wx = new Wx();
        wx.setBh(107);
        wx.setTime("2023-03-10 16:59:33");
        wx.setXx("测试");
        wx.setMc("测试");
        wx.setDz("测试");
        wx.setSbbh("208");
        wx.setJd("114.163053");
        wx.setWd("37.632371");
        recursionOpenId("",wx);
        //getCode();
    }
    
    public static String getCode() throws IOException{
    	String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=123#wechat_redirect";
        url = url.replace("APPID", "wx34bb5f16cd8ad29b");
        url = url.replace("REDIRECT_URI",urlEnodeUTF8("http://fsh01.com/zzytpt/openid_sj.jsp"));
        url = url.replace("SCOPE", "snsapi_userinfo");
        System.out.println(url);
    	return url;
    }
    public static String urlEnodeUTF8(String str){
        String result = str;
        try {
            result = URLEncoder.encode(str,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static String getOpenid(String code) throws IOException, WxErrorException{
    	String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    	url = url.replace("APPID", "wx34bb5f16cd8ad29b");
    	url = url.replace("SECRET", "be567d060925f6d618d3516a85eccc2a");
    	url = url.replace("CODE", code);
    	JSONObject jsonObject = JSONObject.parseObject(sendGet(url));
    	return jsonObject.getString("openid");
    }
    
    public static void push(Wx wx,String openid) throws Exception {
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId("wx34bb5f16cd8ad29b");
        wxStorage.setSecret("be567d060925f6d618d3516a85eccc2a");
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("thing1", wx.getSbbh()),
                new WxMpTemplateData("short_thing2", "风力预警"),
                new WxMpTemplateData("short_thing3", wx.getXx()),
                new WxMpTemplateData("time4", wx.getTime()),
                new WxMpTemplateData("remark", wx.getMc())
        );
        //2,推送消息
        WxMpTemplateMessage templateMessage = null;
        if(wx.getSplj().equals("/uploadfiles/files/111.mp4")) {
        	 templateMessage = WxMpTemplateMessage.builder()
                     .toUser(openid)//要推送的用户openid
                     .templateId("OrSsIme995iMiJsKLYGQ26hsLEVsXVRduDMRcg-IXDs")//模版id
                     .url("http://fsh01.com/zzytpt/cgq/sbgl/viewUi.do?jd="+wx.getJd()+"&wd="+wx.getWd())//点击模版消息要访问的网址
                     .data(data)
                     .build();
        }else {
        	templateMessage = WxMpTemplateMessage.builder()
                    .toUser(openid)//要推送的用户openid
                    .templateId("OrSsIme995iMiJsKLYGQ26hsLEVsXVRduDMRcg-IXDs")//模版id
                    .url("http://fsh01.com/zzytpt"+wx.getSplj())//点击模版消息要访问的网址
                    .data(data)
                    .build();
        }
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }

    }

    //拉取openid
    public static List<String> recursionOpenId(String nextOpenId,Wx wx) throws Exception{
        List<String> openIdList = new ArrayList<String>();
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId("wx34bb5f16cd8ad29b");
        wxStorage.setSecret("be567d060925f6d618d3516a85eccc2a");
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        try {
            //获取所有用户openid
            WxMpUserList openList = wxMpService.getUserService().userList(nextOpenId);
            System.out.println(openList.toString());
            for(String strId :openList.getOpenids()){
                System.out.println(strId);
                openIdList.add(strId);
                int usergroupid =  wxMpService.getUserService().userInfo(strId).getGroupId();
                Long[] tagids  = wxMpService.getUserService().userInfo(strId).getTagIds();
                //获取所有标签，标签相对进行发送
                for (Long tagid:tagids) {
                    if(wx.getBh().longValue()== tagid){
                        push(wx,strId);
                    }
                }
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return openIdList;
    }

    public static String sendGet(String url) throws IOException {
        HttpClient client = null;
        try {
            // 定义一个客户端
            client = new DefaultHttpClient();
            // 定义一个get请求方法
            HttpGet get = new HttpGet(url);
            // response 服务器相应对象, 其中包含了状态信息和服务器返回的数据
            HttpResponse response = client.execute(get);	// 开始执行get方法, 请求网络
            // 获得响应码
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == 200) {
                InputStream is = response.getEntity().getContent();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                is.close();
                String html = baos.toString();	// 把流中的数据转换成字符串, 采用的编码是: utf-8
                baos.close();
                System.out.println(html);
                return html;
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(client != null) {
                client.getConnectionManager().shutdown();	// 关闭连接, 和释放资源
            }
        }
        return null;
    }


    /* public static void main(String args[]) {
            // 获取接口访问凭证
            String accessToken = getToken("wx34bb5f16cd8ad29b", "be567d060925f6d618d3516a85eccc2a").getAccessToken();
            *//**
         * 获取用户信息
         *//*
            WeixinUserInfo user = getUserInfo(accessToken, "oRgi56CQz2XGn8sFiSu4-y24zlWQ");
            System.out.println("OpenID：" + user.getOpenId());
            System.out.println("关注状态：" + user.getSubscribe());
            System.out.println("关注时间：" + user.getSubscribeTime());
            System.out.println("昵称：" + user.getNickname());
            System.out.println("性别：" + user.getSex());
            System.out.println("国家：" + user.getCountry());
            System.out.println("省份：" + user.getProvince());
            System.out.println("城市：" + user.getCity());
            System.out.println("语言：" + user.getLanguage());
            System.out.println("头像：" + user.getHeadImgUrl());
            System.out.println("分组：" + user.getGroupid());
     }*/
    /**public static WeixinUserInfo getUserInfo(String accessToken, String openId) {
        WeixinUserInfo weixinUserInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        System.out.println(requestUrl);
        // 获取用户信息
        JSONObject jsonObject =httpsRequest(requestUrl, "GET", null);
        System.out.println(jsonObject.toString());
        if (null != jsonObject) {
            try {
                weixinUserInfo = new WeixinUserInfo();
                // 用户的标识
                weixinUserInfo.setOpenId(jsonObject.getString("openid"));
                // 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
                weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
                // 用户关注时间
                weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
                // 昵称
                weixinUserInfo.setNickname(jsonObject.getString("nickname"));
                // 用户的性别（1是男性，2是女性，0是未知）
                weixinUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                weixinUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                weixinUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                weixinUserInfo.setCity(jsonObject.getString("city"));
                // 用户的语言，简体中文为zh_CN
                weixinUserInfo.setLanguage(jsonObject.getString("language"));
                // 用户头像
                weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                //
                weixinUserInfo.setGroupid(jsonObject.getString("groupid"));
            } catch (Exception e) {
                if (0 == weixinUserInfo.getSubscribe()) {

                } else {
                    int errorCode = jsonObject.getInt("errcode");
                    String errorMsg = jsonObject.getString("errmsg");
                    System.out.println("获取用户信息失败 errcode:{} errmsg:{}"+errorCode+ errorMsg);
                }
            }
        }
        return weixinUserInfo;
    }*/

}