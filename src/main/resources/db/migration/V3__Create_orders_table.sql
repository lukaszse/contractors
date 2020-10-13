create table orders (
                id int unsigned auto_increment,
                order_date date ,
                contractor_id int,
                price decimal,
                order_name varchar(50),
                order_description varchar_casesensitive(500),

                foreign key (contractor_id) references contractors (id)
);