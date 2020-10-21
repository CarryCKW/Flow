package com.flow.service;

import com.flow.repository.User;

public interface UserInfo {
    boolean checkPassword(String nick, String pwd);
    User getUserByNick(String nick);
}
