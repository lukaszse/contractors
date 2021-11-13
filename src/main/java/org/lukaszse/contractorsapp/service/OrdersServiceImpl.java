package org.lukaszse.contractorsapp.service;

import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.model.Order;
import org.lukaszse.contractorsapp.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService {

    // == fields ==
    private OrdersRepository repository;

    // == constructors ==
    @Autowired
    public OrdersServiceImpl(OrdersRepository repository) {
        this.repository = repository;
    }

/*    @PostConstruct
    public void init() {
        System.out.println("===================================================");
        System.out.println("=========  this is your database print  ===========");
        System.out.println(findAll().toString());
        System.out.println("================  this is the end  ================");
        System.out.println("\n\n");

    }*/

    // == methods ==
    @Override
    public Order getOrder(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public OrdersRepository addOrder(Order order) {     // TODO == duplicated code - to be modified ==
        repository.save(order);                         // TODO == duplicated code - to be modified ==
        return repository;                              // TODO == duplicated code - to be modified ==
    }

    @Override
    public OrdersRepository editOrder(Order order) {
        repository.save(order);
        return repository;
    }

    @Override
    public OrdersRepository deleteOrder(Integer id) {
        repository.deleteById(id);
        return repository;
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

}
