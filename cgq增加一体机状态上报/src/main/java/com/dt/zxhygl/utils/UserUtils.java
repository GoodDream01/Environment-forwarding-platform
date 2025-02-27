package com.dt.zxhygl.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dt.common.utils.SystemConstant;
import com.dt.common.utils.srping.ApplicationContextUtil;
import com.dt.common.utils.srping.ContextHolderUtils;
import com.dt.zxhygl.mvc.base.pojo.User;

public class UserUtils {

	public static User getCurrentUser(){
		HttpServletRequest request = ContextHolderUtils.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(SystemConstant.SES_USER);
		
		return user;
	}
	
}
