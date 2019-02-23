/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Chux
 * Created: Feb 17, 2019
 */

insert into course(id, name) values(10001, 'PHP');
insert into course(id, name) values(10002, 'Java');
insert into course(id, name) values(10003, 'Python');
insert into course(id, name) values(10004, 'C#');


insert into passport(id, number) values(40001, 'E123456');
insert into passport(id, number) values(40002, 'O123456');
insert into passport(id, number) values(40003, 'C123456');


insert into student(id, name, passport_id) values(20001, 'Ekene', 40001);
insert into student(id, name, passport_id) values(20002, 'Obioma', 40002);
insert into student(id, name, passport_id) values(20003, 'Chioma', 40003);


insert into review(id, rating, description, course_id) values(50001, '5', 'Great Course', 10002);
insert into review(id, rating, description, course_id) values(50002, '4', 'Wonderful Course', 10002);
insert into review(id, rating, description, course_id) values(50003, '5', 'Awesome Course', 10003);


insert into student_course(student_id, course_id) values(20001, 10002);
insert into student_course(student_id, course_id) values(20002, 10002);
insert into student_course(student_id, course_id) values(20003, 10002);
insert into student_course(student_id, course_id) values(20001, 10004);