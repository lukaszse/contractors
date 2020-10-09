package org.lukaszse.contractorsapp.orders;

import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.contractors.Contractor;
import org.lukaszse.contractorsapp.contractors.ContractorService;
import org.lukaszse.contractorsapp.util.AttributeNames;
import org.lukaszse.contractorsapp.util.Mappings;
import org.lukaszse.contractorsapp.util.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute(AttributeNames.ORDER_LIST_VIEW, ordersService.findAll());
        return ViewNames.ORDER_LIST;
    }

    @GetMapping(Mappings.ADD_ORDER)
    public String addOrder(Model model) {
        var orderWriter = new OrderWriter(0, "", "");
        model.addAttribute(AttributeNames.ORDER, orderWriter);
        model.addAttribute(AttributeNames.CONTACTOR_LIST, contractorService.findAll());
        return ViewNames.ADD_ORDER;
    }

    @GetMapping(Mappings.EDIT_ORDER)
    public String editOrder(@RequestParam Integer id, Model model) {
        var order = ordersService.getOrder(id);
        var orderWriter = new OrderWriter(order);
        model.addAttribute(AttributeNames.ORDER, orderWriter);
        model.addAttribute(AttributeNames.CONTACTOR_LIST, contractorService.findAll());
        return ViewNames.ADD_ORDER;
    }

    @PostMapping(Mappings.ADD_ORDER)
    public String processAddOrder(@ModelAttribute(AttributeNames.ORDER) OrderWriter submitedOrder) {
        ordersService.addOrder(submitedOrder.toOrder(contractorService));
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
        return ViewNames.VIEW_ORDER;
    }
}
