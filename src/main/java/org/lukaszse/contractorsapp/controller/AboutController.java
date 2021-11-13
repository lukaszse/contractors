package org.lukaszse.contractorsapp.controller;

import org.lukaszse.contractorsapp.util.Mappings;
import org.lukaszse.contractorsapp.util.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping(Mappings.ABOUT)
    String about() {
        return ViewNames.ABOUT;
    }
}
