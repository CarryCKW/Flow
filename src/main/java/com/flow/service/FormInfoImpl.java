package com.flow.service;

import com.flow.dao.TableDao;
import com.flow.repository.Form;
import com.flow.repository.Vocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormInfoImpl implements FormInfo {

    @Autowired
    private TableDao tableDao;

    @Override
    public List<? extends Form> getVocationListByNick(String nick) throws Exception {
        List<Vocation> list = (List<Vocation>) tableDao.searchRoundForm(nick, Vocation.class, null);
        return list;
    }
}
