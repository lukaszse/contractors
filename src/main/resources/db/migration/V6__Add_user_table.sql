create table users
(

    user_name  varchar(20) primary key,
    first_name varchar(100),
    last_name  varchar(100),
    password   varchar(200)
);

insert into users (user_name, first_name, last_name, password) values ('admin', 'John', 'Doe', 'admin');

insert into users (user_name, first_name, last_name, password) values ('james', 'James', 'Smith', '12345');