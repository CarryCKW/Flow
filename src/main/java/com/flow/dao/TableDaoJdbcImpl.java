package com.flow.dao;

import com.flow.combination.VocationTable;
import com.flow.repository.Form;
import com.flow.repository.Vocation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TableDaoJdbcImpl implements TableDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
    }


    @Override
    public List<? extends Form> searchRoundForm(final String nick, Class<? extends Form> clazz, final String uuid) throws Exception {
        if (clazz.equals(Form.class)) {
            String sql = "select * from form where nick = ?";
            List<Form> list = jdbcTemplate.query(sql, new Object[]{nick}, new RowMapper<Form>() {
                @Override
                public Form mapRow(ResultSet resultSet, int i) throws SQLException {
                    Form form = new Form();
                    form.setNick(nick);
                    form.setCreatedate(resultSet.getTimestamp("createdate"));
                    form.setFormstatus(resultSet.getInt("formstatus"));
                    form.setUuid(resultSet.getString("uuid"));
                    form.setFormtype(resultSet.getInt("formtype"));
                    return form;
                }
            });
            return list;
        } else if (clazz.equals(Vocation.class)) {
            String sql = "select * from form, vocation where form.nick = ? and form.uuid = vocation.uuid";
            List<Vocation> list = jdbcTemplate.query(sql, new Object[]{nick}, new RowMapper<Vocation>() {
                @Override
                public Vocation mapRow(ResultSet resultSet, int i) throws SQLException {
                    Vocation vocation = new Vocation();
                    vocation.setNick(nick);
                    vocation.setCreatedate(resultSet.getTimestamp("createdate"));
                    vocation.setFormstatus(resultSet.getInt("formstatus"));
                    vocation.setUuid(resultSet.getString("uuid"));
                    vocation.setFormtype(resultSet.getInt("formtype"));
                    vocation.setStarttime(resultSet.getTimestamp("starttime"));
                    vocation.setLasttime(resultSet.getInt("lasttime"));
                    vocation.setDescript(resultSet.getString("descript"));
                    return vocation;
                }
            });
            return list;
        } else {
            return null;
        }
    }

    @Override
    public <T extends Form> void insertTable(T form, Class<? extends Form> clazz) {

    }
}
