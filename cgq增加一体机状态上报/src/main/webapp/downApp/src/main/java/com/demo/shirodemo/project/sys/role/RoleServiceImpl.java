package com.demo.shirodemo.project.sys.role;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YL
 * @date 2019/4/4 13:38
 * @apiNote
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> selectRoleByUserId(Integer id) {
        List<Role> roles = new ArrayList<>();

        Role admin = new Role();
        admin.setRoleKey("admin");

        roles.add(admin);
        return roles;
    }
}
