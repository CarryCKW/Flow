package com.flow.dao;

import com.flow.repository.Form;

import java.util.List;

public interface TableDao {
    List<? extends Form> searchRoundForm(String nick , Class<? extends Form> clazz, String uuid) throws Exception;
    <T extends Form> void insertTable(T form, Class<? extends Form> clazz);
}
