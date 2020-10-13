create table contractors (
                id int unsigned primary key auto_increment,
                name varchar(100) not null ,
                street varchar(50) not null ,
                property int not null ,
                post varchar(6) not null ,
                city varchar(50) not null ,
                country varchar(50) not null ,
                phone long
);