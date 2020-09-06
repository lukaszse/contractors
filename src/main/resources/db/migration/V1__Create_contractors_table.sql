create table contractors (
                id int unsigned primary key auto_increment,
                name varchar(100) not null,
                street varchar(50),
                property int,
                post varchar(6),
                city varchar(50),
                country varchar(50),
                phone int
);