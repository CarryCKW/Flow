package com.flow.service;

import com.flow.repository.Form;
import com.flow.repository.Vocation;

import java.util.List;

/**
 * @author 蔡小蔚
 */
public interface FormInfo {
    List<? extends Form> getVocationListByNick(String nick) throws Exception;
    void insertVocation(Vocation vocation);
}
