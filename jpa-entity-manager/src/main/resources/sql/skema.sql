/** example **/

create table tabel_pengguna(
    pengguna_id varchar(255) not null unique,
    nama varchar(255) not null,
    alamat varchar(255) not null,
    telepon varchar(255) not null,
    email varchar(255) not null,
    goldarah varchar(10) not null,

    constraint pk_table_pengguna primary key (pengguna_id)
);

create table tabel_kategori(
    kategori_id varchar(255) not null,
    nama varchar(255) not null,
    active boolean not null,

    constraint pk_tabel_kategori_kategori_id primary key (kategori_id)
);

create table tabel_pic(
    pic_id varchar (255) not null,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    email varchar(255) not null,
    address varchar(255) not null,
    kategori_id varchar(255) not null,

    constraint pk_table_pic_pic_id primary key (pic_id),
    constraint fk_tabel_pic_kategori_id foreign key (kategori_id)
    references tabel_kategori (kategori_id)
);

create table tabel_project(
    project_id varchar(255) not null,
    nama varchar(255) not null,
    created_at timestamp not null,
    updated_at timestamp not null,

    constraint pk_tabel_project_project_id primary key (project_id)
);

create table tabel_project_pic(
    project_id varchar(255) not null,
    pic_id varchar(255) not null,


    constraint fk_tabel_project_pic_project_id foreign key (project_id)
    references tabel_project (project_id),
    constraint fk_tabel_project_pic_pic_id foreign key (pic_id)
    references tabel_pic (pic_id)
);