package com.flow.service;

import com.flow.repository.Form;

import java.util.List;

public interface FormInfo {
    List<? extends Form> getVocationListByNick(String nick) throws Exception;
}
