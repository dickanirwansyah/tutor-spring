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

/** SKEMA SIMULASI TABLE **/


/** create table nasabah **/
create table nasabah(
    nasabah_id varchar(255) not null,
    email varchar(255) not null,
    mobile_phone varchar(255) not null,
    phone_home varchar(255),
    active boolean not null,
    alamat varchar(255) not null,

    constraint pk_nasabah_nasabah_id primary key (nasabah_id)

);

insert into nasabah(nasabah_id, email, mobile_phone, phone_home,
active, alamat) values('n/001', 'dickanirwansyah@gmail.com',
'08892929292', '087283834424', true, 'cilandak');

insert into nasabah_details(nasabah_details_id, rekening, saldo, nasabah_id)
values (1, 12345, 1000000, 'n/001');

create table nasabah_details(
    nasabah_details_id bigint not null,
    rekening int(13) not null,
    saldo int not null,
    nasabah_id varchar(255) not null unique,

    constraint pk_nasabah_details_id primary key(nasabah_details_id),
    constraint fk_nasabah_details_nasabah_id foreign key (nasabah_id)
    references nasabah(nasabah_id)
);
