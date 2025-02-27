package com.dt.taje.mvc.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dt.common.utils.RandomValidateCode;
import com.dt.common.utils.SystemConstant;
import com.dt.common.utils.md5.Md5Encode;
import com.dt.taje.mvc.base.dao.LoginDaoI;
import com.dt.taje.mvc.base.modle.bean.UserBean;
import com.dt.taje.mvc.base.service.LoginServiceI;
import com.dt.taje.utils.ComUtils;
import com.dt.taje.utils.IpUtils;
import com.dt.taje.utils.SysConfig;
import com.dt.taje.utils.Tools;
import com.dt.taje.utils.ZiDianUtils;
import com.dt.zxhygl.mvc.base.pojo.LoginLog;
import com.dt.zxhygl.mvc.base.pojo.User;
import com.dt.zxhygl.mvc.base.service.ICommitteeService;
import com.dt.zxhygl.mvc.base.service.ILoginLogService;
import com.dt.zxhygl.mvc.base.service.IOperatorService;
import com.dt.zxhygl.utils.LoginLogUtils;

@Service
public class LoginServiceImpl extends CommonServiceImpl implements LoginServiceI {
	@Autowired
	@Qualifier("loginDaoImpl")
	LoginDaoI loginDaoI;
	
	@Resource
	private IOperatorService iOperatorService;
	
	@Resource
	private ICommitteeService iCommitteeService;
	
	public void setLoginDaoI(LoginDaoI loginDaoI) {
		this.loginDaoI = loginDaoI;
	}
	
	@Resource
	private ILoginLogService iLoginLogService;

	@Override
	public void login(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName=Tools.ConvNull(request.getParameter("userName"));
		String password=Tools.ConvNull(request.getParameter("password"));
//		String type=Tools.ConvNull(request.getParameter("type"));
		String sessionId = Tools.ConvNull(request.getParameter("sessionId"));
		String imageCode=Tools.ConvNull(request.getParameter("imageCode"));
		String isMobile=Tools.ConvNull(request.getParameter("isMobile"),"0");
		String flat = "0";//平台类型0PC，1手机
		if(isMobile.equals("1")){
			flat = "1";
		}
		 
		if(userName.equals("")){		
			if(isMobile.equals("1")){
				ComUtils.PrintWrite(response, "{\"code\":\"500\",\"msg\":\"用户名不能为空！\"}");
			}else{
				ComUtils.PrintWrite(response, "<script>alert('用户名不能为空！')</script>");
			}
			return ;
		}
		if(password.equals("")){
			if(isMobile.equals("1")){
				ComUtils.PrintWrite(response, "{\"code\":\"500\",\"msg\":\"密码不能为空！\"}");
			}else{
				ComUtils.PrintWrite(response, "<script>alert('密码不能为空！')</script>");
			}
			return ;
		}
//		if(imageCode.equals("")){
//			if(isMobile.equals("1")){
//				//ComUtils.PrintWrite(response, "{\"code\":\"500\",\"msg\":\"验证码不能为空！\"}");
//			}else{
//				ComUtils.PrintWrite(response, "<script>alert('验证码不能为空！')</script>");
//				return ;
//			}
//
//		}
		HttpSession session = request.getSession();
//		String trueImageCode = Tools.ConvNull((String)session.getAttribute(RandomValidateCode.RANDOMCODEKEY));
//		if(!imageCode.toUpperCase().equals(trueImageCode.toUpperCase())){
//			ComUtils.PrintWrite(response, "<script>alert('验证码不正确！')</script>");
//			if(isMobile.equals("1")){
//				ComUtils.PrintWrite(response, "{\"code\":\"500\",\"msg\":\"用户名不能为空！\"}");
//			}else{
//				ComUtils.PrintWrite(response, "<script>alert('用户名不能为空！')</script>");
//			}
//			return ;
//		}
		
		userName=Tools.TransactSQLInjection(userName);
		password=Tools.TransactSQLInjection(password);
		
		Md5Encode te=new Md5Encode();
		password = te.Encrypt(password,"MD5");
		
		User user = new User();
		user.setUsername(userName);
		user.setPassword(password);
//		user.setType(type);
		
//		if(type.equals("0")||type.equals("1")||type.equals("2")||type.equals("3")||type.equals("4")){//管理员;市；县区；乡镇；村街道
//			String sql = "select * from t_operator where zcode='"+userName+"' and zpassword='"+password+"' and zrole='"+type+"' ";
			String sql = "select * from t_operator where zcode='"+userName+"' and zpassword='"+password+"'";
			List list = loginDaoI.executeJDBCQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				String ZUID = map.get("ZID").toString();
				String ZUNAME = map.get("ZCODE").toString();
				String ZUXM = map.get("ZNAME").toString();
				String ZUPWD = map.get("ZPASSWORD").toString();
				String ZROLE = map.get("ZROLE").toString();
				String rylx = ZROLE;
				String type = ZROLE;
				String ZQYID = map.get("ZQYID").toString();
				String ZQYMC = map.get("ZQYMC").toString();
				String xszd = map.get("ZXSZD").toString();
	
				UserBean userBean = new UserBean();
				userBean.setZUID(ZUID);
				userBean.setZUNAME(ZUNAME);
				userBean.setZUXM(ZUXM);
				userBean.setZUPWD(ZUPWD);
				userBean.setZROLE(ZROLE);
				userBean.setZQYID(ZQYID);
				userBean.setZQYMC(ZQYMC);
				session.setAttribute(SysConfig.SESSION_USER_INFO, userBean);
				
				/*************************************************************/
				User userresult = new User();
				userresult.setZid(ZUID);
				userresult.setUsername(ZUNAME);
				userresult.setPassword(ZUPWD);
				userresult.setType(type);
				userresult.setXm(ZUXM);
				userresult.setRole(ZROLE);
				userresult.setRylx(rylx);
				userresult.setQyid(ZQYID);
				userresult.setQymc(ZQYMC);
				userresult.setFlat(flat);
				userresult.setXszd(xszd);
				session.setAttribute(SystemConstant.SES_USER, userresult);
				/*************************************************************/
			/*
			 * LoginLog loginLog = LoginLogUtils.getLoginLog(request, true, userresult); try
			 * { iLoginLogService.insert(loginLog); } catch (Exception e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
				if(isMobile.equals("1")){
					ComUtils.PrintWrite(response, "{\"code\":\"200\",\"msg\":\"登录成功！\"}");
				}else{
					ComUtils.PrintWrite(response, "<script>parent.location.href='"+request.getContextPath()+"/index.jsp';</script>");
				}
			}else{
//				LoginLog loginLog = LoginLogUtils.getLoginLog(request, false, user);
//				try {
//					iLoginLogService.insert(loginLog);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				if(isMobile.equals("1")){
					ComUtils.PrintWrite(response, "{\"code\":\"500\",\"msg\":\"用户名或密码错误，请联系管理员注册！\"}");
				}else{
					ComUtils.PrintWrite(response, "<script>alert('用户名或密码错误，请联系管理员注册！')</script>");
				}
				return ;
			}
		
	}

}
