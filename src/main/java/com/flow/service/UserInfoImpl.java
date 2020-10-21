package com.flow.service;

import com.flow.dao.UserDao;
import com.flow.repository.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoImpl implements UserInfo {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean checkPassword(String nick, String pwd) {
        System.out.println("before search nick:" + nick);
        User user = userDao.search(nick);
        System.out.println("afteer search pwd:"+ user.getPassword());
        if (user==null || !user.getPassword().equals(pwd)){
            return false;
        }
        return true;
    }

    @Override
    public User getUserByNick(String nick) {
        return userDao.search(nick);
    }
}
