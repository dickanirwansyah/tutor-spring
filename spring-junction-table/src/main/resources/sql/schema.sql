create database dbcore_jpa;
use dbcore_jpa;

create table person(
    email varchar(255) not null unique,
    nama varchar(255) not null,
    phone varchar(255) not null,

    constraint pk_person_email primary key (email)
);

create table skill(
    skill_id varchar(255) not null,
    nama varchar(255) not null,

    constraint pk_person_skill primary key (skill_id)
);

/** junction table skill - person **/
create table person_skill(
    person_id varchar(255) not null,
    skill_id varchar(255) not null,

    primary key(person_id, skill_id),
    constraint fk_person_skill_person_id foreign key (person_id) references person(email),
    constraint fk_person_skill_skill_id foreign key (skill_id) references skill(skill_id)
);