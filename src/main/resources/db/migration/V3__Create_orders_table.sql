create table orders (
                id int unsigned auto_increment,
                contractor_id int not null,
                order_name varchar(50) not null,
                order_description varchar_casesensitive(500)
);