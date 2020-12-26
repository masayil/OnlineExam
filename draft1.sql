create database OnlineExamSystem
use OnlineExamSystem
create table student(
s_serialNumber bigint identity(1,1) primary key,
s_id nvarchar(12) NOT NULL unique,
s_name nvarchar(20) NOT NULL,
s_password nvarchar(25) NOT NULL,
s_sex nvarchar(5),
s_college nvarchar(50),
s_department nvarchar(70),
s_class nvarchar(30),
s_major nvarchar(30),
);
/*
insert into student values('2201020304','学生1','s040506','男','XXX学校','学院1','学院1-专业1-班级1','学院1-专业1');
insert into student values('2201020301','学生2','s040506','男','XXX学校','学院1','学院1-专业1-班级1','学院1-专业1');
insert into student values('2201020302','学生3','s040506','男','XXX学校','学院1','学院1-专业1-班级2','学院1-专业1');

insert into student values('2201020307','学生x1','s040506','男','XXX学校','学院1','学院1-专业1-班级3','学院1-专业1');
insert into student values('2201020308','学生x2','s040506','男','XXX学校','学院1','学院1-专业1-班级3','学院1-专业1');
insert into student values('2201020309','学生x3','s040506','男','XXX学校','学院1','学院1-专业1-班级3','学院1-专业1');
*/


create table teacher(
t_serialNumber int identity(1,1) primary key,
t_id nvarchar(12) NOT NULL unique,
t_name nvarchar(20) NOT NULL unique,
t_password nvarchar(25) NOT NULL,
t_sex nvarchar(5),
t_college nvarchar(50),
t_department nvarchar(70),
);
/*
insert into teacher values('1101020309','教师12-25','t040506','女','XXX学校','学院1');
insert into teacher values('1101020304','教师1','t040506','女','XXX学校','学院1');
insert into teacher values('1101020311','教师2','t040506','女','XXX学校','学院1');
insert into teacher values('1101020312','教师3','t040506','女','XXX学校','学院2');
insert into teacher values('1101020399','教师4','t040506','男','XXX学校','学院2');
insert into teacher values('1101020371','教师5','t040506','女','XXX学校','学院3');
insert into teacher values('1101020325','教师6','t040506','男','XXX学校','学院4');
*/
create table administrator(
a_serialNumber int identity(1,1) primary key,
a_id nvarchar(12) NOT NULL unique,
a_name nvarchar(20) NOT NULL,
a_password nvarchar(25) NOT NULL,
a_sex nvarchar(5) ,
);
/*
insert into administrator values('0001020304','管理员1','a040506','男');
*/


/*学院专业班级*/
create table majorclass(
majorclass_serialNumber int identity(1,1) primary key,
department nvarchar(50) NOT NULL,
major nvarchar(70) NOT NULL,
class_1 nvarchar(30) NOT NULL unique,
);
/*
insert into majorclass values('学院1','学院1-专业1','学院1-专业1-班级1');
insert into majorclass values('学院1','学院1-专业1','学院1-专业1-班级2');
insert into majorclass values('学院1','学院1-专业2','学院1-专业2-班级1');
insert into majorclass values('学院1','学院1-专业1','学院1-专业1-班级3');
*/
/*开设课程*/
create table course(
course_serialNumber int identity(1,1) primary key,
course_name nvarchar(50) NOT NULL unique,/*课程名字*/
course_creator nvarchar(70) NOT NULL,  /*开设院系*/
);
/*
insert into course values('学院1-课程1','学院1');
insert into course values('学院1-课程2','学院1');
insert into course values('学院1-课程3','学院1');
insert into course values('学院2-课程1','学院2');
insert into course values('学院2-课程2','学院2');
insert into course values('学院3-课程1','学院3');
insert into course values('学院4-课程1','学院4');
*/

/*课程表*/
create table newlesson(
newlesson_serialNumber int identity(1,1) primary key,
newlesson_uuid nvarchar(32) NOT NULL unique,      /*课程表uuid*/
newlesson_name nvarchar(50) NOT NULL,                 /*课程名字*/
newlesson_creatorID nvarchar(12) NOT NULL,             /*老师ID*/
newlesson_class nvarchar(30) NOT NULL,                  /*上课班级*/
newlesson_createDate nvarchar(40), /*课程开设时间*/
);
ALTER TABLE newlesson ADD CONSTRAINT fk_newlesson_one FOREIGN KEY (newlesson_creatorID) 
REFERENCES teacher(t_id)  on update cascade on delete cascade;
ALTER TABLE newlesson ADD CONSTRAINT fk_newlesson_two FOREIGN KEY (newlesson_class) 
REFERENCES majorclass(class_1)  on update cascade on delete cascade;
ALTER TABLE newlesson ADD CONSTRAINT fk_newlesson_three FOREIGN KEY (newlesson_name) 
REFERENCES course(course_name)  on update cascade;
/*
insert into newlesson values('01aed5df1bef4c6988449e7f8de3709c','学院1-课程1','1101020304','学院1-专业1-班级1','2019-09-01 11:00:00');
insert into newlesson values('6a46858b797b49aa96d24d5f02cc4765','学院1-课程2','1101020311','学院1-专业1-班级1','2019-09-02 11:00:00');
insert into newlesson values('b412362ffc7b49699cdd28d4624d4c90','学院1-课程3','1101020304','学院1-专业1-班级1','2019-09-04 11:00:00');
insert into newlesson values('5a7c06cac41e4fa78f5289a364a16776','学院2-课程1','1101020399','学院1-专业1-班级1','2019-09-05 11:00:00');
insert into newlesson values('8849399a05f0428a83b13372cee00fe7','学院3-课程1','1101020371','学院1-专业1-班级1','2019-09-10 11:00:00');
insert into newlesson values('6c44bdd3ee7f4ceeb6ef7f79ade360c9','学院4-课程1','1101020325','学院1-专业1-班级2','2019-09-01 13:41:05');
insert into newlesson values('2a9b988b3294468896f5fdcc88be55c1','学院2-课程2','1101020312','学院1-专业1-班级2','2019-09-01 03:11:15');
*/
select * from student where s_class in (select newlesson_class from newlesson where newlesson_uuid='d2c529138db24e7cb5424b067d9b18fe')

/*重修课程表*/
create table retake(
retake_serialNumber int identity(1,1) primary key,
lessonuuid nvarchar(32) NOT NULL,             /*课程表uuid*/
retake_studentID nvarchar(12) NOT NULL,          /*学生ID*/
);
ALTER TABLE retake ADD CONSTRAINT fk_retake_one FOREIGN KEY (lessonuuid) 
REFERENCES newlesson(newlesson_uuid)  on update cascade on delete cascade;
ALTER TABLE retake ADD CONSTRAINT fk_retake_two FOREIGN KEY (retake_studentID) 
REFERENCES student(s_id)  on update cascade on delete cascade;
/*
insert into retake values('6c44bdd3ee7f4ceeb6ef7f79ade360c9','2201020304');
insert into retake values('01aed5df1bef4c6988449e7f8de3709c','2201020302');
*/

/*题库*//*type:1单选  2多选  3判断  4主观*/
create table questionBank(
questionBank_serialNumber bigint identity(1,1) primary key,
questionBank_creatorID nvarchar(12) NOT NULL,        /*老师ID*/
questionBank_course nvarchar(50) NOT NULL,            /*课程名字*/
questionBank_createDate nvarchar(40),                   /*题目创建时间*/
questionBank_point nvarchar(50) NOT NULL,                /*题目知识点*/
questionBank_type int NOT NULL,                   /*题目类型*/
questionBank_title nvarchar(400) unique,       /*题干*/
questionBank_titleimage nvarchar(100),               /*题干图片*/
questionBank_option1 nvarchar(100),                    
questionBank_option2 nvarchar(100),
questionBank_option3 nvarchar(100),
questionBank_option4 nvarchar(100),
questionBank_answer nvarchar(400),
);
ALTER TABLE questionBank ADD CONSTRAINT fk_questionBank_one FOREIGN KEY (questionBank_course) 
REFERENCES course(course_name)  on update cascade;
ALTER TABLE questionBank ADD CONSTRAINT fk_questionBank_two FOREIGN KEY (questionBank_creatorID) 
REFERENCES teacher(t_id)  on update cascade on delete cascade;
/*
insert into questionBank values ('1101020304','学院1-课程1','2018-09-01 03:11:15','知识点1',4,'问题14','','','','','','简答题答案');
insert into questionBank values ('1101020304','学院1-课程1','2018-09-01 03:11:15','知识点1',1,'问题1','','1+1=2','1+1=2','1+1=2','1+1=2','A');
insert into questionBank values ('1101020304','学院1-课程2','2018-09-01 03:11:15','知识点2',1,'问题2','','1+1=2','1+1=3','1+1=4','1+1=5','B');
insert into questionBank values ('1101020304','学院1-课程1','2018-09-01 03:11:15','知识点2',2,'问题3','','1+1=2','1+1=3','1+1=4','1+1=5','ABC');
insert into questionBank values ('1101020304','学院1-课程2','2018-09-01 03:11:15','知识点2',2,'问题4','','1+1=2','1+1=3','1+1=4','1+1=5','ABCD');
insert into questionBank values ('1101020304','学院1-课程1','2018-09-01 03:11:15','知识点3',3,'问题5','','','','','','T');
insert into questionBank values ('1101020304','学院1-课程2','2018-09-01 03:11:15','知识点2',3,'问题6','','','','','','F');
insert into questionBank values ('1101020304','学院1-课程1','2018-09-01 03:11:15','知识点1',4,'问题7','','','','','','简答题答案');
insert into questionBank values ('1101020304','学院1-课程2','2018-09-01 03:11:15','知识点3',4,'问题8','','','','','','简答题答案');
insert into questionBank values ('1101020304','学院1-课程2','2018-09-01 03:11:15','知识点1',1,'问题9','','9+1=2','9+1=2','9+1=2','9+1=2','A');
insert into questionBank values ('1101020304','学院1-课程2','2018-09-01 03:11:15','知识点3',4,'问题10','','','','','','简答题答案');
insert into questionBank values ('1101020304','学院1-课程1','2018-09-01 03:11:15','知识点1',1,'问题11','','1+1=2','1+1=2','1+1=2','1+1=2','B');
insert into questionBank values ('1101020304','学院1-课程1','2018-09-01 03:11:15','知识点2',2,'问题12','','1+1=2','1+1=3','1+1=4','1+1=5','ABC');
insert into questionBank values ('1101020304','学院1-课程1','2018-09-01 03:11:15','知识点1',4,'问题14','','','','','','简答题答案');
insert into questionBank values ('1101020304','学院1-课程2','2018-09-01 03:11:15','知识点2',3,'问题13','','','','','','F');
*/
/*考试安排*/
create table examAssign(
examAssign_serialNumber int identity(1,1) primary key,
examAssign_uuid nvarchar(32) NOT NULL unique,            /*考试安排uuid*/
lessonuuid nvarchar(32) NOT NULL,                  /*课程表uuid*/
startTime nvarchar(40),
endTime nvarchar(40),
lasttime int,
examAssign_createDate nvarchar(40),       /*创建时间*/
paperbaseuuid nvarchar(32) NOT NULL,             /*试卷uuid*/
examAssign_name nvarchar(40),/*考试名称*/
totalscore float not null,
);
ALTER TABLE examAssign ADD CONSTRAINT fk_examAssign_one FOREIGN KEY (lessonuuid) 
REFERENCES newlesson(newlesson_uuid)  on update cascade on delete cascade;
/*ALTER TABLE examAssign ADD CONSTRAINT fk_examAssign_three FOREIGN KEY (paperbaseuuid) 
REFERENCES paperbase(paperbase_uuid)  on update cascade;*/
/*
insert into examAssign values ('d98d803ea05242a4b7e262a35d6d56d5','01aed5df1bef4c6988449e7f8de3709c','2020-11-25 10:00:00','2020-12-25 10:00:00',100,'2020-10-24 10:00:00','f8bb9bb4eb1a49c89387300d2b20988a','第一次考试',25);
insert into examAssign values ('229f5ae296a14ba0a89236f165fcfc96','6a46858b797b49aa96d24d5f02cc4765','2020-11-20 10:00:00','2020-11-24 10:00:00',100,'2020-10-22 10:00:00','a21f4ef6ac1d484e9fccc640ce7ee5fa','第二次考试',40);
insert into examAssign values ('8f0752c53e804d938c3f4e34298c004f','6a46858b797b49aa96d24d5f02cc4765','2020-12-20 10:00:00','2020-12-25 10:00:00',100,'2020-10-22 10:00:00','a21f4ef6ac1d484e9fccc640ce7ee5fa','第三次考试',40);
*/
create table paperbase(
paperbase_serialNumber bigint identity(1,1) primary key,
paperbase_name nvarchar(30) NOT NULL,                /*试卷名字*/
paperbase_uuid nvarchar(32) NOT NULL,                  /*试卷uuid f8bb9bb4eb1a49c89387300d2b20988a a21f4ef6ac1d484e9fccc640ce7ee5fa*/
paperbase_creatorID nvarchar(12) NOT NULL,          /*老师ID*/
paperbase_createDate nvarchar(40),                 /*创建时间*/
course nvarchar(50) NOT NULL,           /*课程名字*/
questiontype int NOT NULL,                 /*题目类型*/
paperbase_title nvarchar(200),        
paperbase_titleimage nvarchar(100),
paperbase_option1 nvarchar(100),
paperbase_option2 nvarchar(100),
paperbase_option3 nvarchar(100),
paperbase_option4 nvarchar(100),
paperbase_answer nvarchar(500),
score float not null,
);
ALTER TABLE paperbase ADD CONSTRAINT fk_paperbase_one FOREIGN KEY (paperbase_creatorID) 
REFERENCES teacher(t_id)  on update cascade on delete cascade;
ALTER TABLE paperbase ADD CONSTRAINT fk_paperbase_two FOREIGN KEY (course) 
REFERENCES course(course_name)  on update cascade;
/*
insert into paperbase values('试卷模板1','f8bb9bb4eb1a49c89387300d2b20988a','1101020304','2020-12-10 10:00:00','学院1-课程1',1,'问题1','','1+1=2','1+1=2','1+1=2','1+1=2','A',5);
insert into paperbase values('试卷模板1','f8bb9bb4eb1a49c89387300d2b20988a','1101020304','2020-12-10 10:00:00','学院1-课程1',1,'问题11','','1+1=2','1+1=2','1+1=2','1+1=2','B',5);
insert into paperbase values('试卷模板1','f8bb9bb4eb1a49c89387300d2b20988a','1101020304','2020-12-10 10:00:00','学院1-课程1',2,'问题3','','1+1=2','1+1=3','1+1=4','1+1=5','ABC',5);
insert into paperbase values('试卷模板1','f8bb9bb4eb1a49c89387300d2b20988a','1101020304','2020-12-10 10:00:00','学院1-课程1',2,'问题12','','1+1=2','1+1=3','1+1=4','1+1=5','ABC',5);
insert into paperbase values('试卷模板1','f8bb9bb4eb1a49c89387300d2b20988a','1101020304','2020-12-10 10:00:00','学院1-课程1',3,'问题5','','','','','','T',5);
insert into paperbase values('试卷模板1','f8bb9bb4eb1a49c89387300d2b20988a','1101020304','2020-12-10 10:00:00','学院1-课程1',3,'问题13','','','','','','F',5);
insert into paperbase values('试卷模板1','f8bb9bb4eb1a49c89387300d2b20988a','1101020304','2020-12-10 10:00:00','学院1-课程1',4,'问题7','','','','','','简答题答案',10);
insert into paperbase values('试卷模板1','f8bb9bb4eb1a49c89387300d2b20988a','1101020304','2020-12-10 10:00:00','学院1-课程1',4,'问题14','','','','','','简答题答案',10);
insert into paperbase values('试卷模板2','a21f4ef6ac1d484e9fccc640ce7ee5fa','1101020311','2020-12-11 10:00:00','学院1-课程2',1,'问题2','','1+1=2','1+1=3','1+1=4','1+1=5','B',5);
insert into paperbase values('试卷模板2','a21f4ef6ac1d484e9fccc640ce7ee5fa','1101020311','2020-12-11 10:00:00','学院1-课程2',2,'问题4','','1+1=2','1+1=3','1+1=4','1+1=5','ABCD',5);
insert into paperbase values('试卷模板2','a21f4ef6ac1d484e9fccc640ce7ee5fa','1101020311','2020-12-11 10:00:00','学院1-课程2',3,'问题6','','','','','','F',5);
insert into paperbase values('试卷模板2','a21f4ef6ac1d484e9fccc640ce7ee5fa','1101020311','2020-12-11 10:00:00','学院1-课程2',4,'问题8','','','','','','简答题答案',10);
insert into paperbase values('试卷模板2','a21f4ef6ac1d484e9fccc640ce7ee5fa','1101020311','2020-12-11 10:00:00','学院1-课程2',4,'问题10','','','','','','简答题答案',10);
insert into paperbase values('试卷模板2','a21f4ef6ac1d484e9fccc640ce7ee5fa','1101020311','2020-12-11 10:00:00','学院1-课程2',1,'问题9','','9+1=2','9+1=2','9+1=2','9+1=2','A',5);
*/
create table grade(
grade_serialNumber bigint identity(1,1) primary key,
objectivequestion float,
subjectivequestion float,
total float,
studentID nvarchar(12) NOT NULL,
examAssignuuid nvarchar(32) NOT NULL, /*考试安排uuid*/
);
ALTER TABLE grade ADD CONSTRAINT fk_grade_one FOREIGN KEY (studentID) 
REFERENCES student(s_id)  on update cascade on delete cascade;
ALTER TABLE grade ADD CONSTRAINT fk_grade_two FOREIGN KEY (examAssignuuid) 
REFERENCES examAssign(examAssign_uuid)  on update cascade on delete cascade;
/*
insert into grade values (12,0,-1,'2201020301','79cc1ee58c6e4339b048f60ef5837d63');
insert into grade values (15,0,-1,'2201020302','79cc1ee58c6e4339b048f60ef5837d63');
select  examAssignuuid from grade where examAssignuuid='79cc1ee58c6e4339b048f60ef5837d63' and total=-1
*/
create table paper(
paper_serialNumber bigint identity(1,1) primary key,
studentID nvarchar(12) NOT NULL,
examAssignuuid nvarchar(32) NOT NULL, /*考试安排uuid*/
questiontype int NOT NULL,                 /*题目类型*/
paper_title nvarchar(200),        
paper_titleimage nvarchar(100),
paper_option1 nvarchar(100),
paper_option2 nvarchar(100),
paper_option3 nvarchar(100),
paper_option4 nvarchar(100),
paper_answer nvarchar(500),
/*score float not null,*/
youranswer nvarchar(500),
score float not null,
);
ALTER TABLE paper ADD CONSTRAINT fk_paper_one FOREIGN KEY (studentID) 
REFERENCES student(s_id)  on update cascade on delete cascade;
ALTER TABLE paper ADD CONSTRAINT fk_paper_two FOREIGN KEY (examAssignuuid) 
REFERENCES examAssign(examAssign_uuid)  on update cascade on delete cascade;

/*
insert into paper values ('2201020304','229f5ae296a14ba0a89236f165fcfc96',1,'问题2','','1+1=2','1+1=3','1+1=4','1+1=5','B','C',5);
insert into paper values ('2201020304','229f5ae296a14ba0a89236f165fcfc96',2,'问题4','','1+1=2','1+1=3','1+1=4','1+1=5','ABCD','ABCD',5);
insert into paper values ('2201020304','229f5ae296a14ba0a89236f165fcfc96',3,'问题6','','','','','','F','F',5);
insert into paper values ('2201020304','229f5ae296a14ba0a89236f165fcfc96',4,'问题8','','','','','./answersheet/test1.jpg','简答题答案','',10);
insert into paper values ('2201020304','229f5ae296a14ba0a89236f165fcfc96',4,'问题10','','','','','./answersheet/test1.jpg','简答题答案','',10);
insert into paper values ('2201020304','229f5ae296a14ba0a89236f165fcfc96',1,'问题9','','9+1=2','9+1=2','9+1=2','9+1=2','A','A',5);
select * from paper where studentID='2201020304' and examAssignuuid='229f5ae296a14ba0a89236f165fcfc96'
*/


