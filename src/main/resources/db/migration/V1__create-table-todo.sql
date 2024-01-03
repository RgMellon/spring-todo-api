create table todos(
    id bigint not null auto_increment,
    title varchar(100) not null,
    description varchar(200) not null,
    isDone BOOLEAN not null,

    primary key(id)
);