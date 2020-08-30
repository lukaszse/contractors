package org.lukaszse.orders;

import lombok.extern.slf4j.Slf4j;
import org.lukaszse.util.AttributeNames;
import org.lukaszse.util.Mappings;
import org.lukaszse.util.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;

@Slf4j
@Controller
public class OrderController {

    // == fields ==
    private OrdersService ordersService;

    // == constructors ==
    @Autowired
    public OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    // == methods ==
    @GetMapping(Mappings.ORDER_LIST)
    public String viewOrderList(Model model) {
        model.addAttribute(AttributeNames.ORDER_LIST_VIEW, ordersService.findAllView());
        return ViewNames.ORDER_LIST;
    }
}
