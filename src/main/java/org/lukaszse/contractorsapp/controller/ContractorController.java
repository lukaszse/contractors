package org.lukaszse.contractorsapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.model.Contractor;
import org.lukaszse.contractorsapp.service.ContractorService;
import org.lukaszse.contractorsapp.util.AttributeNames;
import org.lukaszse.contractorsapp.util.Mappings;
import org.lukaszse.contractorsapp.util.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Slf4j
@Controller
public class ContractorController {
    private final ContractorService contractorService;

    @Autowired
    public ContractorController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    // == methods ==
    @GetMapping(Mappings.CONTRACTORS_LIST)
    public String contractorListView(Model model) {
        model.addAttribute(AttributeNames.CONTACTOR_LIST, contractorService.findAll());
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
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            return ViewNames.ADD_CONTRACTOR;
        }

        if (contractor.getId() == null) {
            contractorService.addContractor(contractor);
        } else {
            contractorService.editContractor(contractor);
        }
        return "redirect:/" + Mappings.CONTRACTORS_LIST;
    }

    @GetMapping(Mappings.DELETE_CONTRACTOR)
    public String deleteContractor(@RequestParam Integer id) {
        contractorService.deleteContractor(id);
        return "redirect:/" + Mappings.CONTRACTORS_LIST;
    }
}
