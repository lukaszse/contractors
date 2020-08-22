package org.lukaszse.contractors;

import lombok.extern.slf4j.Slf4j;
import org.lukaszse.util.Mappings;
import org.lukaszse.util.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Slf4j
@Controller
public class ContractorController {

    // == final fields ==
    private final ContractorService contractorService;

    // == constructors ==
    @Autowired
    public ContractorController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    // == model attributes ==
    @ModelAttribute
    public List<Contractor> contractorList() {
        return contractorService.findAll();
    }

    @GetMapping(Mappings.CONTACTORS_LIST)
    public String contractorListView() {
        return ViewNames.CONTRACTORS_LIST;
    }
}
