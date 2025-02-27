package com.dt.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dt.common.utils.SystemConstant;

public class SecurityInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SecurityInterceptor...preHandle...");
        //这里可以根据session的用户来判断角色的权限，根据权限来转发不同的页面
        if(request.getSession().getAttribute(SystemConstant.SES_USER) == null) {
        	//一系列处理后发现session已经失效
            if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ //如果是ajax请求响应头会有x-requested-with  
                PrintWriter out = response.getWriter();  
                out.print("loseSession");//session失效
                out.flush();
            }else{
            	//非ajax请求时，session失效的处理
	           // request.getRequestDispatcher("/login.frm").forward(request,response);
	        	//response.sendRedirect(request.getContextPath()+"/login/login.do");
            	if(request.getRequestURI().indexOf("/app/")!=-1){
            		response.sendRedirect(request.getContextPath()+"/app/login/loginIndex.do");
            	}else{
            		response.sendRedirect(request.getContextPath()+"/login.jsp");
            	}
            }
            return false;
        }
		return true;
	}

}
