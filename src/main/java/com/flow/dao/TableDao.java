package com.flow.dao;

import com.flow.repository.Form;

import java.util.List;

/**
 * @author 蔡小蔚
 */
public interface TableDao {
    /**
     * to search for a list of sql result from table form , vocation or ...
     * @param nick the table user's nick
     * @param clazz the class type to search
     * @param uuid the table form's uuid
     * @return list of the Class extends from Form
     * @throws Exception may be not implement
     */
    List<? extends Form> searchRoundForm(String nick , Class<? extends Form> clazz, String uuid) throws Exception;

    /**
     * to update data repository table form , vocation or ...
     * @param form the Class Object which extends from Form
     * @param clazz the T.class
     * @param <T> the type of T must extends from Form
     */
    <T extends Form> void insertTable(T form, Class<? extends Form> clazz);
}
