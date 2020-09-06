package org.lukaszse.contractors;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.math.raw.Mod;
import org.lukaszse.util.AttributeNames;
import org.lukaszse.util.Mappings;
import org.lukaszse.util.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

/*    // == model attributes ==
    @ModelAttribute
    public List<Contractor> contractorList() {
        return contractorService.findAll();
    }*/


    // == methods ==
    @GetMapping(Mappings.CONTACTORS_LIST)
    public String contractorListView(Model model) {
        model.addAttribute(AttributeNames.CONTACTOR_LIST, contractorService.findAll());
        return ViewNames.CONTRACTORS_LIST;
    }

    @GetMapping(Mappings.VIEW_CONTRACTOR)
    public String viewContractor(@RequestParam int id, Model model) {
        Contractor contractor = contractorService.getContractor(id);
        model.addAttribute(AttributeNames.CONTRACTOR, contractor);
        return ViewNames.VIEW_CONTRACTOR;
    }

    @GetMapping(Mappings.ADD_CONTRACTOR)
    public String addContractor(Model model) {
        Contractor newContractor = new Contractor("", 1000);
        log.info("ID FROM GETGMAPPING ADD METHOD = {}", newContractor.getId());
        model.addAttribute(AttributeNames.CONTRACTOR, newContractor);
        return ViewNames.ADD_CONTRACTOR;
    }

    @GetMapping(Mappings.EDIT_CONTRACTOR)
    public String addContractor(@RequestParam int id, Model model) {
        Contractor contractor = contractorService.getContractor(id);
        log.info("ID FROM GETGMAPPING EDIT METHOD = {}", contractor.getId());
        log.info("CONTRACTOR NAME = {}", contractor.getName());
        model.addAttribute(AttributeNames.CONTRACTOR, contractor);
        return ViewNames.ADD_CONTRACTOR;
    }

    @PostMapping(Mappings.ADD_CONTRACTOR)
    public String processAddOrEditContractor(@ModelAttribute(AttributeNames.CONTRACTOR) Contractor contractor) {


        if(contractor.getId() == null) {
            log.info("GETID = {}", contractor.getId());
            log.info("CONTRACTOR NAME = {}", contractor.getName());
            log.info("DODAJEMY");
            contractorService.addContractor(contractor);
        }
        else{
            log.info("GETID = {}", contractor.getId());
            log.info("CONTRACTOR NAME = {}", contractor.getName());
            log.info("EDYTUJEMY");
            contractorService.editContractor(contractor);
        }

        return "redirect:/" + Mappings.CONTACTORS_LIST;
    }

    @GetMapping(Mappings.DELETE_CONTRACTOR)
    public String deleteContractor(@RequestParam int id) {
        contractorService.deleteContractor(id);
        return "redirect:/" + Mappings.CONTACTORS_LIST;
    }
}
