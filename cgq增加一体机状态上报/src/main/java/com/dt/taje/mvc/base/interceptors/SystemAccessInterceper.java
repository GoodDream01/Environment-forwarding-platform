package com.dt.taje.mvc.base.interceptors;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dt.taje.utils.ConfigureUtil;



/**
 * @author leoly
 * 
 */
@Repository
public class SystemAccessInterceper extends HandlerInterceptorAdapter {
	private final Logger logger = Logger.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		String[] noFilters = ConfigureUtil.getStringArray("filterpath.no_filter_setting");
		boolean isFilter = true;
		if (!ArrayUtils.isEmpty(noFilters)) {
			for (String u : noFilters) {
				if (uri.contains(u)) {
					isFilter = false;
					break;
				}
			}
		}

		/*if (isFilter) {
			// Session
			Object obj = request.getSession()
					.getAttribute(SysConfig.SESSION_USER_INFO);
			if (null == obj) {
				logger.info("登录超时！！");
				PrintWriter writer = response.getWriter();
				String loginpath = request.getContextPath()+"/"+ConfigureUtil.getString("loginpath");
				writer.print("<script>top.location='"+loginpath+"';</script>SESSION_TIMEOUT_ERROR");
				IOUtils.closeQuietly(writer);
				return false;
			} else {
				request.setAttribute("LOG_ACCESS_TIME",
						System.currentTimeMillis());
				logger.info(obj + "访问了" + uri);
			}
		}*/
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
		Object obj = request.getAttribute("LOG_ACCESS_TIME");
		if (null != obj) {
			long accessTime = (Long) obj;
			logger.info("处理请求" + request.getRequestURI() + "耗时"
					+ (System.currentTimeMillis() - accessTime) + "毫秒！");
		}
	}
}

