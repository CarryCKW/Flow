package com.flow.repository;

import java.sql.Timestamp;

public class ProcessTime {
    private String uuid;
    private Timestamp ptime;


    @Override
    public String toString() {
        return "ProcessTime{" +
                "uuid='" + uuid + '\'' +
                ", ptime=" + ptime +
                '}';
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setPtime(Timestamp ptime) {
        this.ptime = ptime;
    }

    public String getUuid() {
        return uuid;
    }

    public Timestamp getPtime() {
        return ptime;
    }
}
