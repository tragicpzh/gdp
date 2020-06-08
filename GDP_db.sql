/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/6/8 21:12:58                            */
/*==============================================================*/

drop database if exists gdp;

create database gdp;

use gdp;

drop table if exists administrator;

drop table if exists college;

drop table if exists major;

drop table if exists student;

drop table if exists `subject`;

drop table if exists teacher;

/*==============================================================*/
/* Table: administrator                                         */
/*==============================================================*/
create table administrator
(
   id                   varchar(20) not null,
   `password`           varchar(20) not null,
   `role`               varchar(10) not null,
   nickname             varchar(20),
   head_portrait        varchar(1024),
   phone_number         varchar(10),
   email                varchar(50),
   registration_time    timestamp,
   primary key (id)
);

/*==============================================================*/
/* Table: college                                               */
/*==============================================================*/
create table college
(
   id                   varchar(20) not null,
   `name`               varchar(50) not null,
   address              varchar(50) not null,
   email                varchar(50) not null,
   phone_number         varchar(10) not null,
   primary key (id)
);

/*==============================================================*/
/* Table: major                                                 */
/*==============================================================*/
create table major
(
   id                   varchar(20) not null,
   college_id           varchar(20) not null,
   `name`               varchar(50) not null,
   address              varchar(50) not null,
   email                varchar(50) not null,
   phone_number         varchar(10) not null,
   primary key (id)
);

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   id                   varchar(20) not null,
   major_id             varchar(20) not null,
   subject_id           varchar(20),
   cross_review_student_id varchar(20),
   `password`           varchar(20) not null,
   `role`               varchar(10) not null,
   nickname             varchar(20),
   head_portrait        varchar(1024),
   phone_number         varchar(10),
   email                varchar(50),
   registration_time    timestamp,
   `name`               varchar(50) not null,
   sex                  varchar(10) not null,
   id_type              varchar(30) not null,
   id_number            varchar(30) not null,
   address              varchar(50) not null,
   nationality          varchar(50) not null,
   grade                varchar(4) not null,
   class                varchar(10) not null,
   enrollment_time      time not null,
   open_document        varchar(1024),
   middle_document      varchar(1024),
   paper_document       varchar(1024),
   conclusion_document  varchar(1024),
   open_score1          int,
   open_score2          int,
   open_score3          int,
   middle_score1        int,
   middle_score2        int,
   middle_score3        int,
   conclusion_score1    int,
   conclusion_score2    int,
   conclusion_score3    int,
   paper_score0         int,
   paper_score1         int,
   final_score          decimal(5,2),
   state                varchar(20) not null,
   primary key (id)
);

/*==============================================================*/
/* Table: subject                                               */
/*==============================================================*/
create table `subject`
(
   id                   varchar(20) not null,
   teacher_id           varchar(20) not null,
   review_teacher_id1   varchar(20),
   review_teacher_id2   varchar(20),
   review_teacher_id3   varchar(20),
   `name`               varchar(50) not null,
   direction            varchar(50) not null,
   difficulty           int not null,
   technology           varchar(1024) not null,
   `describe`           varchar(1024) not null,
   `file`               varchar(1024) not null,
   state                varchar(20) not null,
   primary key (id)
);

/*==============================================================*/
/* Table: teacher                                               */
/*==============================================================*/
create table teacher
(
   id                   varchar(20) not null,
   college_id           varchar(20),
   `password`           varchar(20) not null,
   `role`               varchar(10) not null,
   nickname             varchar(20),
   head_portrait        varchar(1024),
   phone_number         varchar(10),
   email                varchar(50),
   registration_time    timestamp,
   `name`               varchar(50) not null,
   sex                  varchar(10) not null,
   id_type              varchar(30) not null,
   id_number            varchar(30) not null,
   address              varchar(50) not null,
   nationality          varchar(50) not null,
   primary key (id)
);

alter table major add constraint FK_Relationship_1 foreign key (college_id)
      references college (id) on delete restrict on update restrict;

alter table student add constraint FK_Relationship_10 foreign key (subject_id)
      references subject (id) on delete restrict on update restrict;

alter table student add constraint FK_Relationship_3 foreign key (major_id)
      references major (id) on delete restrict on update restrict;

alter table student add constraint FK_Relationship_9 foreign key (cross_review_student_id)
      references student (id) on delete restrict on update restrict;

alter table subject add constraint FK_Relationship_4 foreign key (teacher_id)
      references teacher (id) on delete restrict on update restrict;

alter table subject add constraint FK_Relationship_5 foreign key (review_teacher_id1)
      references teacher (id) on delete restrict on update restrict;

alter table subject add constraint FK_Relationship_6 foreign key (review_teacher_id2)
      references teacher (id) on delete restrict on update restrict;

alter table subject add constraint FK_Relationship_7 foreign key (review_teacher_id3)
      references teacher (id) on delete restrict on update restrict;

alter table teacher add constraint FK_Relationship_2 foreign key (college_id)
      references college (id) on delete restrict on update restrict;

