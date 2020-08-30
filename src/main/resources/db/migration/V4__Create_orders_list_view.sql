create view orders_list as
        select orders.id as order_id,
        orders.contractor_id as contractor_id,
        contractors.name as contractor_name,
        orders.number as order_number,
        orders.name as order_name
        from orders inner join contractors on orders.contractor_id = contractors.id
        order by orders.id;