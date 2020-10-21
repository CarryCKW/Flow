package com.flow.combination;

import com.flow.repository.Form;
import com.flow.repository.Vocation;

import java.sql.Timestamp;

public class VocationTable extends Form{

    private Vocation vocation;

    public void setVocation(Vocation vocation) {
        this.vocation = vocation;
    }

    public Vocation getVocation() {
        return vocation;
    }
}
