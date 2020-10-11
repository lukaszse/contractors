package org.lukaszse.contractorsapp.orders;

import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.contractors.ContractorService;
import org.lukaszse.contractorsapp.orders.DTO.OrderReader;
import org.lukaszse.contractorsapp.orders.DTO.OrderWriter;
import org.lukaszse.contractorsapp.settings.SettingService;
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
    private SettingService settingService;

    // == constructors ==
    @Autowired
    public OrderController(OrdersService ordersService, ContractorService contractorService, SettingService settingService) {
        this.ordersService = ordersService;
        this.contractorService = contractorService;
        this.settingService = settingService;
    }

    // == methods ==
    @GetMapping(Mappings.ORDER_LIST)
    public String viewOrderList(Model model) {
        model.addAttribute(AttributeNames.ORDER_LIST_VIEW, ordersService.findAll());
        return ViewNames.ORDER_LIST;
    }

    @GetMapping(Mappings.ADD_ORDER)
    public String addOrder(Model model) {
        var orderReader = new OrderReader();
        model.addAttribute(AttributeNames.ORDER, orderReader);
        model.addAttribute(AttributeNames.CONTACTOR_LIST, contractorService.findAll());
        return ViewNames.ADD_ORDER;
    }

    @GetMapping(Mappings.EDIT_ORDER)
    public String editOrder(@RequestParam Integer id, Model model) {
        var order = ordersService.getOrder(id);
        var orderReader = new OrderReader(order);
        model.addAttribute(AttributeNames.ORDER, orderReader);
        model.addAttribute(AttributeNames.CONTACTOR_LIST, contractorService.findAll());
        return ViewNames.ADD_ORDER;
    }

    @PostMapping(Mappings.ADD_ORDER)
    public String processAddOrder(@ModelAttribute(AttributeNames.ORDER) OrderWriter submitedOrder) {
        log.info("OrderWriter PRICE from form: " + submitedOrder.getPrice());
        log.info("OrderWriter NAME from form: " + submitedOrder.getOrderName());
        log.info("OrderWriter DESCRIPTION from form: " + submitedOrder.getOrderDescription());
        ordersService.addOrder(submitedOrder.toOrder(ordersService, contractorService));
        return "redirect:/" + Mappings.ORDER_LIST;
    }

    @GetMapping(Mappings.DELETE_ORDER)
    public String deleteOrder(@RequestParam Integer id) {
        ordersService.deleteOrder(id);
        return "redirect:/" + Mappings.ORDER_LIST;
    }

    @GetMapping(Mappings.VIEW_ORDER)
    public String orderView(@RequestParam Integer id, Model model) {
        var orderReader = new OrderReader(ordersService.getOrder(id));
        model.addAttribute(AttributeNames.ORDER, orderReader);
        model.addAttribute("settingsSet", settingService.getCurrentSettings());
        log.info("Order description: " + orderReader.getOrderDescription() + " price after processing " + orderReader.getPrice());
        return ViewNames.VIEW_ORDER;
    }
}
