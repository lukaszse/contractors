package org.lukaszse.orders;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService{

    // == fields ==
    private OrdersRepository repository;
    private OrderViewRepository viewRepository;

    // == constructors ==
    @Autowired
    public OrdersServiceImpl(OrdersRepository repository, OrderViewRepository viewRepository) {
        this.repository = repository;
        this.viewRepository = viewRepository;
    }

    @PostConstruct
    public void init() {
        System.out.println("===================================================");
        System.out.println("=========  this is your database print  ===========");
        System.out.println(findAll().toString());
        System.out.println("================  this is the end  ================");
        System.out.println("\n\n");
        System.out.println("===================================================");
        System.out.println("=========  this is your database print  ===========");
        System.out.println(findAllView().toString());
        System.out.println("================  this is the end  ================");
    }

    // == methods ==
    @Override
    public Order getOrder(int id) {
        return repository.getOne(id);
    }

    @Override
    public OrdersRepository addOrder(Order order) {     // == duplicated code - to be modified ==
        repository.save(order);                         // == duplicated code - to be modified ==
        return repository;                              // == duplicated code - to be modified ==
    }

    @Override
    public OrdersRepository editOrder(Order order) {
        repository.save(order);
        return repository;
    }

    @Override
    public OrdersRepository deleteOrder(int id) {
        repository.deleteById(id);
        return repository;
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public List<OrderView> findAllView() {
        return viewRepository.findAll();
    }
}
