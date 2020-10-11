package org.lukaszse.contractorsapp.orders;

import java.util.List;

public interface OrdersService {
    Order getOrder(Integer id);
    OrdersRepository addOrder(Order order);
    OrdersRepository editOrder(Order order);
    OrdersRepository deleteOrder(Integer id);
    List<Order> findAll();
}
