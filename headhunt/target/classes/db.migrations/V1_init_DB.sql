create sequence hibernate_sequence start 1 increment 1;
create table certificate (
    id int8 not null,
    filename varchar(1048) not null,
    title varchar(255) not null,
    author_id int8 not null,
    primary key (id),
    date_create timestamp not null
);

create table resume (
    id int8 not null,
    about_me varchar(1048),
    birthday date not null,
    education varchar(1048),
    email varchar(55) not null,
    filename varchar(1048),
    hobby varchar(255),
    mobile_number varchar(55)  not null,
    firstname varchar(55) not null,
    profession varchar(55) not null,
    skills varchar(1048),
    lastname varchar(55) not null,
    working_experience varchar(1048),
    author_id int8,
    primary key (id),
    date_update timestamp not null,
    date_create timestamp not null
    );

create table user_role (
    user_id int8 not null,
     roles varchar(255)
    );

create table usr (
    id int8 not null,
    activation_code varchar(255),
    active boolean not null,
    email varchar(55) not null,
    password varchar(255) not null,
    username varchar(55) not null,
    primary key (id),
    firstname varchar(55),
    lastname varchar(55)
    );

alter table if exists certificate
    add constraint certificate_user_fk
    foreign key (author_id) references usr on delete cascade;

alter table if exists resume
    add constraint resume_user_fk
    foreign key (author_id) references usr on delete cascade;

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr;



