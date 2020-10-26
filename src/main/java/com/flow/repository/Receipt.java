package com.flow.repository;

import java.sql.Timestamp;

/**
 * @author 蔡小蔚
 */
public class Receipt extends Form {
    private Timestamp time;
    private Float amount;
    private String descript;

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Timestamp getTime() {
        return time;
    }

    public Float getAmount() {
        return amount;
    }

    public String getDescript() {
        return descript;
    }


}
