package org.lukaszse.contractorsapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.model.Order;
import org.lukaszse.contractorsapp.model.dto.OrderDto;
import org.lukaszse.contractorsapp.repository.OrdersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final ContractorService contractorService;

    public Order getOrder(Integer id) {
        return ordersRepository.getById(id);
    }

    public Page<Order> getPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Order> orders = ordersRepository.findAll();
        List<Order> list;
        if (orders.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, orders.size());
            list = orders.subList(startItem, toIndex);
        }
        return new PageImpl<Order>(list, PageRequest.of(currentPage, pageSize), orders.size());
    }

    public void addEditOrder(OrderDto orderDto) {
        ordersRepository.save(createOrderOrGetOrderForUpdate(orderDto));
    }

    public void deleteOrder(Integer id) {
        ordersRepository.deleteById(id);
    }

    public List<Order> findAll() {
        return ordersRepository.findAll();
    }

    private Order createOrderOrGetOrderForUpdate(final OrderDto orderDto) {
        return orderDto.getId() == null ?
                createOrder(orderDto) :
                findOrderAndPrepareForUpdate(orderDto);
    }

    private Order createOrder(final OrderDto orderDto) {
        return new Order(contractorService.getContractor(orderDto.getContractorId()),
                new BigDecimal(orderDto.getPrice()),
                orderDto.getOrderName(),
                orderDto.getOrderDescription());
    }

    private Order findOrderAndPrepareForUpdate(final OrderDto orderDto) {
        Order order = getOrder(orderDto.getId());
        order.setContractor(contractorService.getContractor(orderDto.getContractorId()));
        order.setPrice(new BigDecimal(orderDto.getPrice()));
        order.setOrderName(orderDto.getOrderName());
        order.setOrderDescription(orderDto.getOrderDescription());
        return order;
    }

}
