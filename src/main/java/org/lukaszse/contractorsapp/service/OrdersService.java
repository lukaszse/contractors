package org.lukaszse.contractorsapp.service;

import org.lukaszse.contractorsapp.model.Order;
import org.lukaszse.contractorsapp.repository.OrdersRepository;

import java.util.List;

public interface OrdersService {
    Order getOrder(Integer id);
    OrdersRepository addOrder(Order order);
    OrdersRepository editOrder(Order order);
    OrdersRepository deleteOrder(Integer id);
    List<Order> findAll();
}
