package com.demo.shirodemo.configure.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author YL
 * @date 2019/3/27 15:53
 * @apiNote
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {

    /**
     * header中token标志
     */
    private static String LOGIN_SIGN = "x-access-token";


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                if (e instanceof AuthorizationException) {
                    throw new AuthorizationException("访问资源权限不足！");
                } else {
                    //token 异常 认证失败
                    throw new AuthenticationException("token 异常 认证失败");
                }
            }
        }
        return true;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        //判断是否是登录请求
        String authorization = req.getHeader(LOGIN_SIGN);
        return authorization != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String header = req.getHeader(LOGIN_SIGN);
        JWTToken token = new JWTToken(header);
        getSubject(request, response).login(token);

        return true;
    }
}
