package com.demo.shirodemo.project.sys.user;

import com.demo.shirodemo.common.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YL
 * @date 2019/4/4 16:55
 * @apiNote
 */
@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping("save")
    @RequiresPermissions("sys:user:save")
    public R save() {
        return R.success();
    }

    @RequestMapping("delete")
    @RequiresPermissions("sys:user:delete")
    public R delete() {
        return R.success();
    }

}
