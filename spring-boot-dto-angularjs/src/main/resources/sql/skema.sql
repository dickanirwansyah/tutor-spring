create table skill(
    skill_id int not null auto_increment,
    name varchar(255) not null,

    constraint pk_skill_skill_id primary key (skill_id)
);

create table users(
    users_id int not null auto_increment,
    name varchar(255) not null,

    constraint pk_users_users_id primary key (users_id)
);

create table users_skills(
    users_users_id int not null,
    skills_skills_id int not null,

    constraint fk_users_skills_skills_id primary key (skills_skills_id),
    constraint fk_users_skills_users_id foreign key (users_users_id) references
    users(users_id)
);