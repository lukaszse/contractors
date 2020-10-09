create table orders (
                id int unsigned auto_increment,
                contractor_id int,
                order_name varchar(50),
                order_description varchar_casesensitive(500),

                foreign key (contractor_id) references contractors (id)
);