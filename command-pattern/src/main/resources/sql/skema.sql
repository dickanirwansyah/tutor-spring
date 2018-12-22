/** schema database **/
create table pengguna(
    email varchar(255) not null unique,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    aktiv varchar(255) not null,
    created_at date not null,
    updated_at date not null,

    constraint pk_pengguna_email primary key (email)
);

create table medsos(
    medsos_id varchar(255) not null unique,
    nama varchar(255) not null,
    link varchar(255) not null,

    constraint pk_medsos_medsos_id primary key (medsos_id)
);

create table pengguna_medsos(
    email varchar(255) not null,
    medsos_id varchar(255) not null,
    created_at date not null,

    constraint primary key (email, medsos_id),
    constraint fk_pengguna_medsos_email foreign key (email) references pengguna(email),
    constraint fk_pengguna_medsos_medsos_id foreign key (medsos_id) references medsos(medsos_id)
);