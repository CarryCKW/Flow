package com.flow.repository;

import java.sql.Timestamp;

public class Vocation  extends Form {
    private Timestamp starttime;
    private int lasttime;
    private String descript;

    @Override
    public String toString() {
        return "Vocation{" + " starttime=" + starttime +
                ", lasttime=" + lasttime +
                ", descript='" + descript + '\'' +
                '}';
    }


    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public void setLasttime(int lasttime) {
        this.lasttime = lasttime;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Timestamp getStarttime() {
        return starttime;
    }

    public int getLasttime() {
        return lasttime;
    }

    public String getDescript() {
        return descript;
    }
}
