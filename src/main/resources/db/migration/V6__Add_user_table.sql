create table users
(

    user_name  varchar(20) primary key,
    first_name varchar(100),
    last_name  varchar(100),
    password   varchar(500)
);

insert into users (user_name, first_name, last_name, password) values ('admin', 'John', 'Doe', '{bcrypt}$2a$12$rvC3VtPFdeubVO0pOeMBa.yYdygRBm2NT.lK7y4PC2FI4Mu88FLIK');

insert into users (user_name, first_name, last_name, password) values ('user', 'James', 'Smith', '{bcrypt}$2a$12$feoSS.Dx/rRdQWfWHeWYZu8txsYcy8Dxt89MWd9U3O8r4CaAKKY3S');