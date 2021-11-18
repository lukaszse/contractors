package org.lukaszse.contractorsapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.model.Contractor;
import org.lukaszse.contractorsapp.model.User;
import org.lukaszse.contractorsapp.service.ContractorService;
import org.lukaszse.contractorsapp.util.AttributeNames;
import org.lukaszse.contractorsapp.util.Mappings;
import org.lukaszse.contractorsapp.util.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
@Controller
public class ContractorController {
    private final ContractorService contractorService;

    @Autowired
    public ContractorController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }


    @GetMapping(Mappings.CONTRACTORS_LIST)
    public String userListView(@RequestParam(name = "pageNumber", defaultValue = "1") final int pageNumber,
                               @RequestParam(name = "pageSize", defaultValue = "5") final int pageSize,
                               final Model model) {
        Page<Contractor> usersPage = contractorService.getPaginated(PageRequest.of(pageNumber - 1, pageSize));
        model.addAttribute(AttributeNames.CONTRACTOR_PAGE, usersPage);
        Stream.of(usersPage.getTotalPages())
                .filter(totalPages -> totalPages > 0)
                .map(totalPages -> IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList()))
                .forEach(pageNumbers -> model.addAttribute(AttributeNames.PAGE_NUMBERS, pageNumbers));
        return ViewNames.CONTRACTORS_LIST;
    }

    @GetMapping(Mappings.VIEW_CONTRACTOR)
    public String viewContractor(@RequestParam Integer id, Model model) {
        model.addAttribute(AttributeNames.CONTRACTOR, contractorService.getContractor(id));
        return ViewNames.VIEW_CONTRACTOR;
    }

    @GetMapping(Mappings.ADD_CONTRACTOR)
    public String addContractor(Model model) {
        model.addAttribute(AttributeNames.CONTRACTOR, new Contractor());
        return ViewNames.ADD_CONTRACTOR;
    }

    @GetMapping(Mappings.EDIT_CONTRACTOR)
    public String addContractor(@RequestParam Integer id, Model model) {
        model.addAttribute(AttributeNames.CONTRACTOR, contractorService.getContractor(id));
        return ViewNames.ADD_CONTRACTOR;
    }

    @Transactional
    @PostMapping(Mappings.ADD_CONTRACTOR)
    public String processAddOrEditContractor(
            @ModelAttribute(AttributeNames.CONTRACTOR) @Valid Contractor contractor,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ViewNames.ADD_CONTRACTOR;
        }
        contractorService.addEditContractor(contractor);
        return "redirect:/" + Mappings.CONTRACTORS_LIST;
    }

    @GetMapping(Mappings.DELETE_CONTRACTOR)
    public String deleteContractor(@RequestParam Integer id) {
        contractorService.deleteContractor(id);
        return "redirect:/" + Mappings.CONTRACTORS_LIST;
    }
}
