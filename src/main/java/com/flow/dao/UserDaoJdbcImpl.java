package com.flow.dao;

import com.flow.repository.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJdbcImpl implements UserDao{

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
    }

    @Override
    public User search(final String nick) {
        String sql = "select userid, nick, password, userstatus from users where nick = ?";
        User user = null;
        try{
            user = this.jdbcTemplate.queryForObject(sql, new Object[]{nick}, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet resultSet, int i) throws SQLException {
                    User user = new User();
                    user.setUserstatus(resultSet.getInt("userstatus"));
                    user.setUserid(resultSet.getInt("userid"));
                    user.setNick(nick);
                    user.setPassword(resultSet.getString("password"));
                    return user;
                }
            });
            assert user != null;
            System.out.println(user.toString());
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return user;
    }
}
