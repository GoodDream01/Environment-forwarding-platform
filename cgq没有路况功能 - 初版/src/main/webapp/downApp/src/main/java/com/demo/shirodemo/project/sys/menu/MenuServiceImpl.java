package com.demo.shirodemo.project.sys.menu;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YL
 * @date 2019/4/4 12:47
 * @apiNote
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Override
    public List<Menu> selectPermsByUserId(Integer id) {
        Menu saveUser = new Menu();
        saveUser.setPerms("sys:user:save");
//        Menu deleteUser = new Menu();
//        deleteUser.setPerms("sys:user:delete");

        List<Menu> menus = new ArrayList<>();
        menus.add(saveUser);
//        menus.add(deleteUser);
        return menus;
    }
}
