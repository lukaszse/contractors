package org.lukaszse.contractorsapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.model.Order;
import org.lukaszse.contractorsapp.model.dto.OrderDto;
import org.lukaszse.contractorsapp.repository.OrderRepository;
import org.lukaszse.contractorsapp.repository.OrderSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderSearchRepository orderSearchRepository;
    private final ContractorService contractorService;

    public Order getOrder(Integer id) {
        return orderRepository.getById(id);
    }

    public Page<Order> getPaginated(final Pageable pageable) {
        return orderSearchRepository.findAll(pageable);
    }

    public Page<Order> findOrders(final String orderName, final String contractor, final Pageable pageable) {
        return orderSearchRepository.findOrdersByOrderNameContainsAndContractor_NameContainsIgnoreCase(orderName, contractor, pageable);
    }

    public void addEditOrder(OrderDto orderDto) {
        orderRepository.save(createOrderOrGetOrderForUpdate(orderDto));
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    private Order createOrderOrGetOrderForUpdate(final OrderDto orderDto) {
        return orderDto.getId() == null ?
                createOrder(orderDto) :
                findOrderAndPrepareForUpdate(orderDto);
    }

    private Order createOrder(final OrderDto orderDto) {
        return new Order(contractorService.getContractor(orderDto.getContractorId()),
                new BigDecimal(orderDto.getPrice().replace(",", ".")),
                orderDto.getOrderName(),
                orderDto.getOrderDescription());
    }

    private Order findOrderAndPrepareForUpdate(final OrderDto orderDto) {
        Order order = getOrder(orderDto.getId());
        order.setContractor(contractorService.getContractor(orderDto.getContractorId()));
        order.setPrice(new BigDecimal(orderDto.getPrice().replace(",", ".")));
        order.setOrderName(orderDto.getOrderName());
        order.setOrderDescription(orderDto.getOrderDescription());
        return order;
    }

}
