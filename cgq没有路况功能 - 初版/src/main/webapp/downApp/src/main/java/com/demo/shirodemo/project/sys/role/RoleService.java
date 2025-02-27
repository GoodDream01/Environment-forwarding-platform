package com.demo.shirodemo.project.sys.role;

import java.util.List;

/**
 * @author YL
 * @date 2019/4/4 12:37
 * @apiNote
 */
public interface RoleService {
    List<Role> selectRoleByUserId(Integer id);
}
