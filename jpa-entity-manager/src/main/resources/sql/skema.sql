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

