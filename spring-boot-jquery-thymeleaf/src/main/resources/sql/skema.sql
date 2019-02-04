create table person(
    person_id int auto_increment,
    name varchar(255) not null,
    address varchar(255) not null,
    phone varchar(255) not null,
    created_at datetime not null,
    updated_at datetime not null,

    constraint pk_person_personId primary key (person_id)
);

create table person_credit(
    person_id int not null,
    credit_id int not null,

    primary key (person_id, credit_id),
    constraint fk_person_credit_person_id foreign key (person_id)
    references person(person_id),
    constraint fk_person_credit_credit_id foreign key (credit_id)
    references credit(credit_id)
);

create table credit(
    credit_id int auto_increment,
    name varchar(255) not null,
    created_at datetime not null,
    updated_at datetime not null,
    info_id varchar(255) not null,

    constraint pk_credit_credit_id primary key (credit_id),
    constraint fk_credit_info_id foreign key (info_id) references info(info_id)
);

create table info(
    info_id varchar(255) not null,
    name varchar(255) not null,
    created_at datetime not null,
    updated_at datetime not null,

    constraint pk_info_info_id primary key (info_id)
);