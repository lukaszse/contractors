package org.lukaszse.contractorsapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.model.dto.OrderViewDto;
import org.lukaszse.contractorsapp.model.dto.OrderCreateDto;
import org.lukaszse.contractorsapp.service.ContractorService;
import org.lukaszse.contractorsapp.service.OrdersService;
import org.lukaszse.contractorsapp.service.SettingService;
import org.lukaszse.contractorsapp.util.AttributeNames;
import org.lukaszse.contractorsapp.util.Mappings;
import org.lukaszse.contractorsapp.util.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrdersService ordersService;
    private final ContractorService contractorService;
    private final SettingService settingService;

    // == methods ==
    @GetMapping(Mappings.ORDER_LIST)
    public String viewOrderList(Model model) {
        model.addAttribute(AttributeNames.ORDER_LIST_VIEW, ordersService.findAll());
        return ViewNames.ORDER_LIST;
    }

    @GetMapping(Mappings.ADD_ORDER)
    public String addOrder(Model model) {
        var orderReader = new OrderViewDto();
        model.addAttribute(AttributeNames.ORDER, orderReader);
        model.addAttribute(AttributeNames.CONTACTOR_LIST, contractorService.findAll());
        return ViewNames.ADD_ORDER;
    }

    @GetMapping(Mappings.EDIT_ORDER)
    public String editOrder(@RequestParam Integer id, Model model) {
        var order = ordersService.getOrder(id);
        var orderReader = new OrderViewDto(order);
        model.addAttribute(AttributeNames.ORDER, orderReader);
        model.addAttribute(AttributeNames.CONTACTOR_LIST, contractorService.findAll());
        return ViewNames.ADD_ORDER;
    }

    @PostMapping(Mappings.ADD_ORDER)
    public String processAddOrder(
            @ModelAttribute(AttributeNames.ORDER) @Valid OrderCreateDto submittedOrder,
            BindingResult bindingResult,
            Model model) {
        if (!bindingResult.hasErrors()) {
            log.info("OrderWriter PRICE from form: " + submittedOrder.getPrice());
            log.info("OrderWriter NAME from form: " + submittedOrder.getOrderName());
            log.info("OrderWriter DESCRIPTION from form: " + submittedOrder.getOrderDescription());
            ordersService.addOrder(submittedOrder.toOrder(ordersService, contractorService));
            return "redirect:/" + Mappings.ORDER_LIST;
        }
        model.addAttribute(AttributeNames.ORDER, submittedOrder);
        model.addAttribute(AttributeNames.CONTACTOR_LIST, contractorService.findAll());
        return ViewNames.ADD_ORDER;
    }

    @GetMapping(Mappings.DELETE_ORDER)
    public String deleteOrder(@RequestParam Integer id) {
        ordersService.deleteOrder(id);
        return "redirect:/" + Mappings.ORDER_LIST;
    }

    @GetMapping(Mappings.VIEW_ORDER)
    public String orderView(@RequestParam Integer id, Model model) {
        var orderReader = new OrderViewDto(ordersService.getOrder(id));
        model.addAttribute(AttributeNames.ORDER, orderReader);
        model.addAttribute("settingsSet", settingService.getCurrentSettings());
        log.info("Order description: " + orderReader.getOrderDescription() + " price after processing " + orderReader.getPrice());
        return ViewNames.VIEW_ORDER;
    }
}
