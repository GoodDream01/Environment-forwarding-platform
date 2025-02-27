package com.demo.shirodemo.configure.shiro;


import com.demo.shirodemo.project.sys.menu.Menu;
import com.demo.shirodemo.project.sys.menu.MenuService;
import com.demo.shirodemo.project.sys.role.Role;
import com.demo.shirodemo.project.sys.role.RoleService;
import com.demo.shirodemo.project.sys.user.User;
import com.demo.shirodemo.project.sys.user.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @author yl
 * @apiNote 用户登录时认证和授权
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 认证:校验帐号和密码
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        //从token中获取用户名
        String username = JWTUtils.getUsername(token);
        //获取数据库中存取的用户，密码是加密后的
        User user = userService.selectByUserName(username);
        if (user != null) {
            // 密码验证
            if (!JWTUtils.verify(token, username, user.getPassword())) {
                throw new IncorrectCredentialsException();
            }
            return new SimpleAuthenticationInfo(token, token, getName());
        } else {
            throw new UnknownAccountException();
        }
    }

    /**
     * 授权:授予角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = JWTUtils.getUsername(principals.toString());
        User user = userService.selectByUserName(userName);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (user != null) {
            //赋予角色
            List<Role> roles = roleService.selectRoleByUserId(user.getId());
            for (Role role : roles) {
                info.addRole(role.getRoleKey());
            }
            //赋予资源
            List<Menu> permissions = menuService.selectPermsByUserId(user.getId());
            for (Menu permission : permissions) {
                info.addStringPermission(permission.getPerms());
            }
        }
        return info;
    }

}