package com.demo.shirodemo.project.sys.menu;

import java.util.List;

/**
 * @author YL
 * @date 2019/4/4 12:38
 * @apiNote
 */
public interface MenuService {
    List<Menu> selectPermsByUserId(Integer id);
}
