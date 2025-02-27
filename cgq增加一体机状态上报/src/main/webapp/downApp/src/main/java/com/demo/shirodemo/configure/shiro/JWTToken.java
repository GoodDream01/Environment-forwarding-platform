package com.demo.shirodemo.configure.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author YL
 * @date 2019/3/27 15:52
 * @apiNote
 */
public class JWTToken implements AuthenticationToken {

    /**
     * 密钥
     */
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
