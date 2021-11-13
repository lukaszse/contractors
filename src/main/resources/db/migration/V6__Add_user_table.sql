create table users
(

    user_name  varchar(20) primary key,
    first_name varchar(100),
    last_name  varchar(100),
    password   varchar(200)
);

insert into users (user_name, first_name, last_name, password) values ('admin', 'John', 'Doe', '{bcrypt}$2a$10$Owq6qu3kHrYpwoFB5PMnZuG.CRfZ0A/I3QgJCeJd1X/ai2t/PXjMm');

insert into users (user_name, first_name, last_name, password) values ('james', 'James', 'Smith', '{bcrypt}$2a$10$.6TcNyCQ4Gj68L9ESwT33ufoEZefOwWfn8KMV8ucekt1qE9FIXwLS');