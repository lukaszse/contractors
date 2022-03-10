package org.lukaszse.contractorsapp.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.model.Order;
import org.lukaszse.contractorsapp.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderEndpoint {

    private final OrderService ordersService;

    @GetMapping("/findOrders")
    public ResponseEntity<Page<Order>> findOrders(@RequestParam final String orderName,
                                                  @RequestParam final String contractor,
                                                  @RequestParam(name = "pageNumber", defaultValue = "1") final int pageNumber,
                                                  @RequestParam(name = "pageSize", defaultValue = "5") final int pageSize) {

        var orders = ordersService.findOrders(orderName, contractor, PageRequest.of(pageNumber - 1, pageSize, Sort.by("id")));
        return ResponseEntity.ok(orders);
    }
}
