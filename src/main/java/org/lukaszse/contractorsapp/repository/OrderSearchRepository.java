package org.lukaszse.contractorsapp.repository;

import org.lukaszse.contractorsapp.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderSearchRepository extends PagingAndSortingRepository<Order, Integer> {

    Page<Order> findOrdersByOrderNameContainsAndContractor_NameContainsIgnoreCase(final String orderName, final String contractor, final Pageable pageable);
}
