set names utf8mb4;
create database flow;
use flow;
create table users(
    userid int not null,
    nick varchar(30) not null,
    password varchar(30) not null,
    userstatus int not null,
    primary key(userid)
);
create table form(
    userid int not null,
    formid int not null,
    formtype int not null,
    formstatus int not null,
    createdate datetime not null,
    lifetime int default 7 not null,
    date1 datetime default null,
    date2 datetime default null,
    date3 datetime default null,
    date4 datetime default null,
    primary key(formid)
);
create table vocation(
    formid int not null,
    starttime datetime not null,
    lasttime int not null,
    descript varchar(300),
    primary key(formid)
);
create table receipt(
    formid int not null,
    time datetime not null,
    amount float default 0.0 not null,
    descript varchar(300),
    primary key(formid)
);
create table official(
    formid int not null,
    time datetime not null,
    authorid int not null,
    primary key(formid)
);
create table flowdes(
    formtype int not null,
    need int not null,
    pre1 int not null,
    pre2 int not null,
    pre3 int not null,
    pre4 int not null,
    primary key(formtype)
);


insert into users values(1,'root','admin',0);
insert into users values(2,'admin1','admin',1);
insert into users values(3,'admin2','admin',1);
insert into users values(4,'admin3','admin',1);
insert into users values(5,'stu','stu',2);

insert into form(userid,formid,formtype,formstatus,createdate,lifetime) values(5,1,1,0,'2020-10-01 12:00:00',7);
insert into form(userid,formid,formtype,formstatus,createdate,lifetime) values(5,2,2,0,'2020-10-02 12:00:00',7);
insert into form(userid,formid,formtype,formstatus,createdate,lifetime) values(5,3,3,0,'2020-10-03 12:00:00',7);

insert into vocation values(1,'2020-10-01 12:00:00',7,'vocation descript');

insert into receipt values(2,'2020-10-01 12:00:00',1000.0,'receipt descript');

insert into official values(3,'2020-10-01 12:00:00',1);

insert into flowdes values(1,7,0,1,3,0);
insert into flowdes values(2,7,0,1,1,0);
insert into flowdes values(3,15,0,0,0,7);
