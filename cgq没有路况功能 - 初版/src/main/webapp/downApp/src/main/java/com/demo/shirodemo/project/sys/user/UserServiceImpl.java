package com.demo.shirodemo.project.sys.user;

import org.springframework.stereotype.Service;

/**
 * @author YL
 * @date 2019/4/4 12:44
 * @apiNote
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User selectByUserName(String username) {
        User user = new User();
        user.setUserName(username);
        user.setPassword("dc483e80a7a0bd9ef71d8cf973673924");

        return user;
    }
}
