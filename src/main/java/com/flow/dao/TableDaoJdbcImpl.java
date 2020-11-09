package com.flow.dao;

import com.flow.exdException.DataOpException;
import com.flow.repository.Form;
import com.flow.repository.Receipt;
import com.flow.repository.Vocation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 蔡小蔚
 */
public class TableDaoJdbcImpl implements TableDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
    }


    @Override
    public List<? extends Form> searchRoundForm(final String nick, final Class<? extends Form> clazz, final String uuid) {
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
        } else if (clazz.equals(Receipt.class)){
            String sql = "select * from form, receipt where nick = ? and form.uuid = receipt.uuid";
            List<Receipt> list = jdbcTemplate.query(sql, new Object[]{nick}, new RowMapper<Receipt>() {
                @Override
                public Receipt mapRow(ResultSet resultSet, int i) throws SQLException {
                    Receipt receipt = new Receipt();
                    receipt.setAmount(resultSet.getFloat("amount"));
                    receipt.setDescript(resultSet.getString("descript"));
                    receipt.setTime(resultSet.getTimestamp("time"));
                    receipt.setCreatedate(resultSet.getTimestamp("createdate"));
                    receipt.setFormstatus(resultSet.getInt("formtype"));
                    receipt.setNick(nick);
                    receipt.setUuid(resultSet.getString("uuid"));
                    return receipt;
                }
            });
            return list;
        } else {
            return null;
        }
    }

    @Override
    public < T extends Form> void insertTable(T form, Class<? extends Form> clazz, Object... objects) throws DataOpException{
        if (clazz.equals(Form.class)){
            String sql = "insert into form(uuid, nick, formtype, formstatus, createdate) VALUES (?, ?, ?, ?, ?)";
//            PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(sql,
//                    new int[]{Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.TIMESTAMP});
//            KeyHolder keyHolder = new GeneratedKeyHolder();
//            int count = jdbcTemplate.update(preparedStatementCreatorFactory.newPreparedStatementCreator(new Object[]{
//                    form.getUuid(), form.getNick(), form.getFormtype(), form.getFormstatus(),form.getCreatedate()}), keyHolder);
            Object[] objects1 = new Object[]{form.getUuid(), form.getNick(), form.getFormtype(), form.getFormstatus(),form.getCreatedate()};
            int[] args1 = new int[]{Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.TIMESTAMP};
            int count1 = jdbcTemplate.update(sql, objects1, args1);
            if (count1!=1) {
                throw new DataOpException("cant insert into table form.");
            }
            return;
        } else if (clazz.equals(Vocation.class)){
            insertTable(form, Form.class);
            String sql = "insert into vocation(uuid, starttime, lasttime, descript) VALUES (?, ?, ?, ?)";
//            PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(sql,
//                    new int[]{Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER,Types.VARCHAR});
//            KeyHolder keyHolder = new GeneratedKeyHolder();
//            int count = jdbcTemplate.update(preparedStatementCreatorFactory.newPreparedStatementCreator(new Object[]{
//                    form.getUuid(),  objects[0], objects[1], objects[2]}), keyHolder);
            Object[] objects2 = new Object[]{form.getUuid(),  objects[0], objects[1], objects[2]};
            int[] args2 = new int[]{Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER,Types.VARCHAR};
            int count2 = jdbcTemplate.update(sql, objects2, args2);
            if (count2!=1) {
                throw new DataOpException("cant insert into table form.");
            }
            return;
        } else if (clazz.equals(Receipt.class)){
            insertTable(form, Form.class);
            String sql = "insert into receipt(uuid, time, amount, descript) VALUES (?, ?, ?, ?)";
            Object[] objects3 = new Object[]{form.getUuid(), objects[0], objects[1], objects[2]};
            int[] args3 = new int[]{Types.VARCHAR, Types.TIMESTAMP, Types.FLOAT, Types.VARCHAR};
            int count3 = jdbcTemplate.update(sql, objects3, args3);
            if (count3!=1) {
                throw  new DataOpException("can't insert into table receipt");
            }
            return;
        } else {
            throw new DataOpException("not implement this class.");
        }
    }

    @Override
    public <T extends Form> void updateTable(T form, Class<? extends Form> clazz, Object... objects) throws DataOpException {
        String sql = "update form set formstatus = ? where uuid = ?";
        int count = jdbcTemplate.update(sql, new Object[]{form.getFormstatus(), form.getUuid()}, new int[]{Types.INTEGER, Types.VARCHAR});
        if (count != 1){
            throw new DataOpException("can't update formstatus in method {# com.flow.dao.TableDaoJdbcImpl.updataTable}");
        }


    }

    @Override
    public <T extends Form> void updateTables(List<T> form, Class<? extends Form> clazz) throws DataOpException {
        Iterator<T> iterator = form.iterator();
        while (iterator.hasNext()) {
            try {
                updateTable(iterator.next(), clazz);
            }
            catch (DataOpException de) {
                de.printStackTrace();
                throw de;
            }
        }
    }
    @Override
    public List<? extends Form> searchallvocation()  {
        String sql = "select nick from users where userstatus = ?";

        List<String> listn = jdbcTemplate.query(sql, new Object[]{2}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String string = resultSet .getString("nick");
                return string;
            }
        });
        List<Vocation> listv = (List<Vocation>) searchRoundForm(listn.get(0), Vocation.class, null);
        for (int i =1 ;i< listn.size();i++)
        {
            List<Vocation> l = (List<Vocation>) searchRoundForm(listn.get(i), Vocation.class, null);
            for (int j =0 ; j<l.size();j++) {
                listv.add(l.get(j));
            }
        }
        return listv;
    }
    @Override
    public Vocation GetVocationByid(String uuid) {

        String sql = "select * from form, vocation where form.uuid = ? and form.uuid = vocation.uuid";
        List<Vocation> list = jdbcTemplate.query(sql, new Object[]{uuid}, new RowMapper<Vocation>() {
            @Override
            public Vocation mapRow(ResultSet resultSet, int i) throws SQLException {
                Vocation vocation = new Vocation();
                vocation.setNick(resultSet.getString("nick"));
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
        return list.get(0) ;
    }



    @Override
    public List<? extends Form> getAllTables(Class<? extends Form> clazz) {
        if (Form.class.equals(clazz)) {
            String sql = "select uuid, nick, formtype, formstatus, createdate from form ";
            List<Form> list = jdbcTemplate.query(sql, new RowMapper<Form>() {
                @Override
                public Form mapRow(ResultSet resultSet, int i) throws SQLException {
                    Form form = new Form();
                    form.setUuid(resultSet.getString("uuid"));
                    form.setFormtype(resultSet.getInt("formtype"));
                    form.setFormstatus(resultSet.getInt("formstatus"));
                    form.setNick(resultSet.getString("nick"));
                    return form;
                }
            });
            return list;
        }else {
            throw new DataOpException("not implement {#getAllTables} with param: " + clazz);
        }
    }
}
