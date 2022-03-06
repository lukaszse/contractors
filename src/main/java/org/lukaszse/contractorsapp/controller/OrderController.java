package org.lukaszse.contractorsapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.model.Order;
import org.lukaszse.contractorsapp.model.dto.OrderDto;
import org.lukaszse.contractorsapp.model.dto.OrderViewDto;
import org.lukaszse.contractorsapp.service.ContractorService;
import org.lukaszse.contractorsapp.service.OrderService;
import org.lukaszse.contractorsapp.service.SettingService;
import org.lukaszse.contractorsapp.util.AttributeNames;
import org.lukaszse.contractorsapp.util.Mappings;
import org.lukaszse.contractorsapp.util.ViewNames;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService ordersService;
    private final ContractorService contractorService;
    private final SettingService settingService;


    @GetMapping(Mappings.ADD_ORDER)
    public String addOrderView(final Model model) {
        model.addAttribute(AttributeNames.ORDER, new OrderViewDto());
        model.addAttribute(AttributeNames.CONTACTOR_LIST, contractorService.findAll());
        return ViewNames.ADD_ORDER;
    }

    @GetMapping(Mappings.EDIT_ORDER)
    public String editOrderView(@RequestParam final Integer id, Model model) {
        var order = ordersService.getOrder(id);
        var orderReader = new OrderViewDto(order);
        model.addAttribute(AttributeNames.ORDER, orderReader);
        model.addAttribute(AttributeNames.CONTACTOR_LIST, contractorService.findAll());
        return ViewNames.ADD_ORDER;
    }

    @GetMapping(Mappings.ORDER_LIST)
    public String orderListView(@RequestParam(name = "pageNumber", defaultValue = "1") final int pageNumber,
                                @RequestParam(name = "pageSize", defaultValue = "5") final int pageSize,
                                final Model model) {
        Page<Order> orderPage = ordersService.getPaginated(PageRequest.of(pageNumber - 1, pageSize));
        model.addAttribute(AttributeNames.ORDER_PAGE, orderPage);
        Stream.of(orderPage.getTotalPages())
                .filter(totalPages -> totalPages > 0)
                .map(totalPages -> IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList()))
                .forEach(pageNumbers -> model.addAttribute(AttributeNames.PAGE_NUMBERS, pageNumbers));
        return ViewNames.ORDER_LIST;
    }

    @PostMapping(Mappings.ADD_ORDER)
    public String addOrder(
            @ModelAttribute(AttributeNames.ORDER) @Valid final OrderDto submittedOrder,
            final BindingResult bindingResult, final Model model) {
        if (!bindingResult.hasErrors()) {
            ordersService.addEditOrder(submittedOrder);
            return "redirect:/" + Mappings.ORDER_LIST;
        }
        model.addAttribute(Map.of(
                AttributeNames.ORDER, submittedOrder,
                AttributeNames.CONTACTOR_LIST, contractorService.findAll()));
        return ViewNames.ADD_ORDER;
    }

    @GetMapping(Mappings.DELETE_ORDER)
    public String deleteOrder(@RequestParam final Integer id) {
        ordersService.deleteOrder(id);
        return "redirect:/" + Mappings.ORDER_LIST;
    }

    @GetMapping(Mappings.VIEW_ORDER)
    public String orderView(@RequestParam final Integer id, final Model model) {
        var orderReader = new OrderViewDto(ordersService.getOrder(id));
        model.addAllAttributes(Map.of(
                AttributeNames.ORDER, orderReader,
                "settingsSet", settingService.getCurrentSettings()));
        log.info("Order description: " + orderReader.getOrderDescription() + " price after processing " + orderReader.getPrice());
        return ViewNames.VIEW_ORDER;
    }
}
