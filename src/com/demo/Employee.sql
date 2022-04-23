create database Library_Management_System;
use Library_Management_System;
create table Employee(
empId int primary key,
empName varchar(25),
empMail varchar(25),
empPhone varchar(25),
password varchar(25),
countbooks int);
desc Employee;