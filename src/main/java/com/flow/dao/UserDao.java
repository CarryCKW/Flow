package com.flow.dao;

import com.flow.repository.User;

/**
 * @author 蔡小蔚
 */
public interface UserDao {
    /**
     * find a user by userid
     * @param nick nick
     * @return User
     */
    User search(String nick);

}
