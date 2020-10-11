create table settings (

    id int unsigned primary key auto_increment,
    company_name varchar(100),
    street varchar(100),
    property int,
    post varchar(100),
    city varchar(100),
    country varchar(100),
    phone int
);

insert into settings (company_name, street, property, post, city, country, phone)
                        values ('Twoja Firma', 'Twoja Ulica', 10, '43-100', 'Miasto', 'Polska', 797343234);