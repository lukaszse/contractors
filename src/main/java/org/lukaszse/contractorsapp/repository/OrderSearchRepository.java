package org.lukaszse.contractorsapp.repository;

import org.lukaszse.contractorsapp.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderSearchRepository extends PagingAndSortingRepository<Order, Integer> {

    List<Order> findOrdersByOrderNameContaining(final String orderName, final Pageable pageable);
}
