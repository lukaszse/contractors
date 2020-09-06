package org.lukaszse.orders;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.lukaszse.contractors.Contractor;
import org.lukaszse.contractors.ContractorService;
import org.lukaszse.util.AttributeNames;
import org.lukaszse.util.Mappings;
import org.lukaszse.util.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Column;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class OrderController {

    // == fields ==
    private OrdersService ordersService;
    private ContractorService contractorService;

    // == constructors ==
    @Autowired
    public OrderController(OrdersService ordersService, ContractorService contractorService) {
        this.ordersService = ordersService;
        this.contractorService = contractorService;
    }

    // == methods ==
    @GetMapping(Mappings.ORDER_LIST)
    public String viewOrderList(Model model) {
        model.addAttribute(AttributeNames.ORDER_LIST_VIEW, ordersService.findAllView());
        return ViewNames.ORDER_LIST;
    }

    @GetMapping(Mappings.ADD_ORDER)
    public String addOrder(Model model) {
        Order order = new Order(null,null, "");
        model.addAttribute(AttributeNames.ORDER, order);
        model.addAttribute(AttributeNames.CONTACTOR_LIST, contractorService.findAll());
        return ViewNames.ADD_ORDER;
    }

    @GetMapping(Mappings.EDIT_ORDER)
    public String editOrder(@RequestParam Integer id, Model model) {
        Order order = ordersService.getOrder(id);
        model.addAttribute(AttributeNames.ORDER, order);
        model.addAttribute(AttributeNames.CONTACTOR_LIST, contractorService.findAll());
        return ViewNames.ADD_ORDER;
    }

    @PostMapping(Mappings.ADD_ORDER)
    public String processAddOrder(@ModelAttribute(AttributeNames.ORDER) Order submitedOrder) {
        Order order = submitedOrder;
        ordersService.addOrder(order);
        return "redirect:/" + Mappings.ORDER_LIST;
    }

    @GetMapping(Mappings.DELETE_ORDER)
    public String deleteOrder(@RequestParam Integer id) {
        ordersService.deleteOrder(id);
        return "redirect:/" + Mappings.ORDER_LIST;
    }

    @GetMapping(Mappings.VIEW_ORDER)
    public String orderView(@RequestParam Integer id, Model model) {
        model.addAttribute(ordersService.getOrder(id));
        model.addAttribute(contractorService.getContractor(
                ordersService.getOrder(id).getContractorId()));
        return ViewNames.VIEW_ORDER;
    }
}
