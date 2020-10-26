package com.flow.exdException;

import org.springframework.dao.DataAccessException;

/**
 * @author 蔡小蔚
 */
public class DataOpException extends DataAccessException {
    private String s;

    public DataOpException(String msg) {
        super(msg);
        this.s = msg;
    }

}
