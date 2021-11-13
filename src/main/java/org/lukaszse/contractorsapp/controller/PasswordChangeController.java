package org.lukaszse.contractorsapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.model.dto.PasswordChangeDto;
import org.lukaszse.contractorsapp.service.UserService;
import org.lukaszse.contractorsapp.util.AttributeNames;
import org.lukaszse.contractorsapp.util.Mappings;
import org.lukaszse.contractorsapp.util.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PasswordChangeController {

    private final UserService userService;

    @GetMapping(Mappings.PASSWORD_CHANGE)
    public String changePassword(Model model) {
        model.addAttribute(AttributeNames.PASSWORD_CHANGE, new PasswordChangeDto());
        return ViewNames.PASSWORD_CHANGE;
    }

    @PostMapping(Mappings.PASSWORD_CHANGE)
    public String performPasswordChange(
            @ModelAttribute(AttributeNames.PASSWORD_CHANGE) @Valid PasswordChangeDto passwordChangeDto,
            BindingResult bindingResult,
            Model model, Principal principal) {
        if (!bindingResult.hasErrors()) {
            log.info("Changing password for user={}", principal.getName());
            userService.changePassword(principal.getName(), passwordChangeDto);
            model.addAttribute(AttributeNames.MESSAGE, "Password successfully updated!");
        }
        model.addAttribute(AttributeNames.PASSWORD_CHANGE, passwordChangeDto);
        return ViewNames.PASSWORD_CHANGE;
    }
}
