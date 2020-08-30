package org.lukaszse.orders;

import org.aspectj.weaver.ast.Or;
import org.lukaszse.contractors.Contractor;
import org.lukaszse.contractors.ContractorsRepository;

import java.util.List;

public interface OrdersService {
    Order getOrder(int id);
    OrdersRepository addOrder(Order order);
    OrdersRepository editOrder(Order order);
    OrdersRepository deleteOrder(int id);
    List<Order> findAll();
}
