package com.flow.exdException;

import org.springframework.dao.DataAccessException;

/**
 * @author 蔡小蔚
 */
public class InvalidGraphException extends DataAccessException {

    private String msg;

    public InvalidGraphException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
