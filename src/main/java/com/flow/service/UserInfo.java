package com.flow.service;

import com.flow.repository.User;

/**
 * @author 蔡小蔚
 */
public interface UserInfo {
    /**
     * weather the user pass check
     * @param nick user's nick
     * @param pwd the password user input
     * @return boolen
     */
    boolean checkPassword(String nick, String pwd);

    /**
     * return User
     * @param nick user's nick
     * @return Object User
     */
    User getUserByNick(String nick);
}
