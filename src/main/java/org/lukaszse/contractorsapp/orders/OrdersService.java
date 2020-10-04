package org.lukaszse.contractorsapp.orders;

import java.util.List;

public interface OrdersService {
    Order getOrder(int id);
    OrdersRepository addOrder(Order order);
    OrdersRepository editOrder(Order order);
    OrdersRepository deleteOrder(int id);
    List<Order> findAll();
    List<OrderView> findAllView();
}
