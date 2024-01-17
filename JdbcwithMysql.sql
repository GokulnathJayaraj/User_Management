use jdbcdemo;


CREATE TABLE users(
id INT auto_increment,
uname varchar(20),
age INT,
city varchar(20),
PRIMARY KEY (id) );

insert into users(uname,age,city) values('Ram',18,'chennai');
insert into users(uname,age,city) values('Sam',19,'chennai');
select *from users;
