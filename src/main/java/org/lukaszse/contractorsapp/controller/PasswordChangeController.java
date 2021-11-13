package org.lukaszse.contractorsapp.controller;

import org.lukaszse.contractorsapp.util.Mappings;
import org.lukaszse.contractorsapp.util.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PasswordChangeController {

    @GetMapping(Mappings.PASSWORD_CHANGE)
    String passwordChange() {
        return ViewNames.PASSWORD_CHANGE;
    }
}
