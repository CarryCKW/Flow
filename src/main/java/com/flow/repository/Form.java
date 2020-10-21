package com.flow.repository;

import java.sql.Timestamp;
import java.util.Objects;

public class Form {
    private String uuid;
    private String nick;
    private int formtype;
    private int formstatus;
    private Timestamp createdate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Form form = (Form) o;
        return formtype == form.formtype &&
                formstatus == form.formstatus &&
                uuid.equals(form.uuid) &&
                nick.equals(form.nick) &&
                createdate.equals(form.createdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, nick, formtype, formstatus, createdate);
    }

    @Override
    public String toString() {
        return "Form{" +
                ", uuid='" + uuid + '\'' +
                ", nick='" + nick + '\'' +
                ", formtype=" + formtype +
                ", formstatus=" + formstatus +
                ", createdate=" + createdate +
                '}';
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setFormtype(int formtype) {
        this.formtype = formtype;
    }

    public void setFormstatus(int formstatus) {
        this.formstatus = formstatus;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public String getUuid() {
        return uuid;
    }

    public String getNick() {
        return nick;
    }

    public int getFormtype() {
        return formtype;
    }

    public int getFormstatus() {
        return formstatus;
    }

    public Timestamp getCreatedate() {
        return createdate;
    }
}
