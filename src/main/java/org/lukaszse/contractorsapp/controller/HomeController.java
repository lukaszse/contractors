package org.lukaszse.contractorsapp.controller;

import org.lukaszse.contractorsapp.util.Mappings;
import org.lukaszse.contractorsapp.util.ViewNames;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(Mappings.HOME)
    public String goHome() {
        return ViewNames.HOME;
    }
}
