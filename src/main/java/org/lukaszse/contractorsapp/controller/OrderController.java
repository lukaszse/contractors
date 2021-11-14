package org.lukaszse.contractorsapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.model.Order;
import org.lukaszse.contractorsapp.model.dto.OrderViewDto;
import org.lukaszse.contractorsapp.model.dto.OrderDto;
import org.lukaszse.contractorsapp.service.ContractorService;
import org.lukaszse.contractorsapp.service.OrdersService;
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrdersService ordersService;
    private final ContractorService contractorService;
    private final SettingService settingService;


    @GetMapping(Mappings.ADD_ORDER)
    public String addOrderView(Model model) {
        var orderReader = new OrderViewDto();
        model.addAttribute(AttributeNames.ORDER, orderReader);
        model.addAttribute(AttributeNames.CONTACTOR_LIST, contractorService.findAll());
        return ViewNames.ADD_ORDER;
    }

    @GetMapping(Mappings.EDIT_ORDER)
    public String editOrderView(@RequestParam Integer id, Model model) {
        var order = ordersService.getOrder(id);
        var orderReader = new OrderViewDto(order);
        model.addAttribute(AttributeNames.ORDER, orderReader);
        model.addAttribute(AttributeNames.CONTACTOR_LIST, contractorService.findAll());
        return ViewNames.ADD_ORDER;
    }

    @GetMapping(Mappings.ORDER_LIST)
    public String orderListView(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                                Model model) {

        Page<Order> orderPage = ordersService.getPaginated(PageRequest.of(pageNumber - 1, pageSize));
        model.addAttribute(AttributeNames.ORDER_PAGE, orderPage);

        int totalPages = orderPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute(AttributeNames.PAGE_NUMBERS, pageNumbers);
        }
        return ViewNames.ORDER_LIST;
    }

    @PostMapping(Mappings.ADD_ORDER)
    public String addOrder(
            @ModelAttribute(AttributeNames.ORDER) @Valid OrderDto submittedOrder,
            BindingResult bindingResult,
            Model model) {
        if (!bindingResult.hasErrors()) {
            ordersService.addEditOrder(submittedOrder);
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
