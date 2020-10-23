package com.flow.repository;

import java.sql.Timestamp;

/**
 * @author 蔡小蔚
 */
public class Vocation  extends Form {

    private String stime;

    private Timestamp starttime;
    private int lasttime;
    private String descript;

    @Override
    public String toString() {
        return "Vocation{" +
                "stime='" + stime + '\'' +
                ", starttime=" + starttime +
                ", lasttime=" + lasttime +
                ", descript='" + descript + '\'' +
                '}';
    }

    public void setStarttime(String s){
        this.starttime = Timestamp.valueOf(s);
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

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }


}
