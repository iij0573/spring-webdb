create table member(
id varchar2(100) primary key,
name varchar2(100),
email varchar2(100),
password varchar2(100),
tel varchar2(100)
);

CREATE TABLE INFO(
infoNum int auto_increment primary key,
ID VARCHAR(100),
TITLE VARCHAR(100),
constraint infoNum_FK FOREIGN KEY(ID) REFERENCES MEMBER(id),
constraint infoNum_FK2 FOREIGN KEY(TITLE) REFERENCES BOOK(TITLE));

create table bookinfo(
infonum int auto_increment,
id varchar(100), foreign key(id) references member(id),
title varchar(100), foreign key(title) references book(title),
primary key(infonum));
