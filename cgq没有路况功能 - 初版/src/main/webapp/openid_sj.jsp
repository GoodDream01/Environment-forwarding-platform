<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.dt.cgq.mvc.controller.GzhtsController"%>
<%@page import="me.chanjar.weixin.mp.api.*"%>
<%@page import="me.chanjar.weixin.mp.api.impl.WxMpServiceImpl"%>
<%@page import="me.chanjar.weixin.common.exception.WxErrorException"%>
<% 
	String code = request.getParameter("code");
    GzhtsController wx = new GzhtsController(); 
	String openid = wx.getOpenid(code);
	WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
	wxStorage.setAppId("wx34bb5f16cd8ad29b");
	wxStorage.setSecret("be567d060925f6d618d3516a85eccc2a");
	WxMpService wxMpService = new WxMpServiceImpl();
	wxMpService.setWxMpConfigStorage(wxStorage);
	Long[] tagids = null;
	try {
		tagids = wxMpService.getUserService().userInfo(openid).getTagIds();
	} catch (WxErrorException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	boolean flag = false;
	for (Long tagid:tagids) {
	    if(109 == tagid){
	    	response.sendRedirect("http://fsh01.com/zzytpt/indexsj.jsp");
	    	return;
	    }
	}
	response.sendRedirect("http://fsh01.com/zzytpt/indexwqx.jsp");
%>