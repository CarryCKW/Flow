package com.flow.repository;


/**
 * @author 蔡小蔚
 */
public class User {
    private int userid;
    private String nick;
    private String password;
    private int userstatus;

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserstatus(int userstatus) {
        this.userstatus = userstatus;
    }

    public int getUserid() {
        return userid;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }

    public int getUserstatus() {
        return userstatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", nick='" + nick + '\'' +
                ", password='" + password + '\'' +
                ", userstatus=" + userstatus +
                '}';
    }
}
