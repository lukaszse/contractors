package org.lukaszse.contractorsapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.model.Order;
import org.lukaszse.contractorsapp.model.User;
import org.lukaszse.contractorsapp.model.dto.PasswordChangeDto;
import org.lukaszse.contractorsapp.service.UserService;
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
import java.security.Principal;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(Mappings.PASSWORD_CHANGE)
    public String changePassword(final Model model) {
        model.addAttribute(AttributeNames.PASSWORD_CHANGE, new PasswordChangeDto());
        return ViewNames.PASSWORD_CHANGE;
    }

    @PostMapping(Mappings.PASSWORD_CHANGE)
    public String performPasswordChange(
            @ModelAttribute(AttributeNames.PASSWORD_CHANGE) @Valid final PasswordChangeDto passwordChangeDto,
            BindingResult bindingResult,
            Model model, Principal principal) {
        if (!bindingResult.hasErrors()) {
            log.info("Changing password for user={}", principal.getName());
            userService.changePassword(principal.getName(), passwordChangeDto);
            model.addAttribute(AttributeNames.MESSAGE, "Password successfully updated!");
            model.addAttribute(AttributeNames.PASSWORD_CHANGE, new PasswordChangeDto());
        } else {
            model.addAttribute(AttributeNames.PASSWORD_CHANGE, passwordChangeDto);
        }
        return ViewNames.PASSWORD_CHANGE;
    }

    @GetMapping(Mappings.USER_ADMINISTRATION)
    public String userListView(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                                Model model) {
        Page<User> usersPage = userService.getPaginated(PageRequest.of(pageNumber - 1, pageSize));
        model.addAttribute(AttributeNames.USER_PAGE, usersPage);
        Stream.of(usersPage.getTotalPages())
                .filter(totalPages -> totalPages > 0)
                .map(totalPages -> IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList()))
                .forEach(pageNumbers -> model.addAttribute(AttributeNames.PAGE_NUMBERS, pageNumbers));
        return ViewNames.USER_ADMINISTRATION;
    }
}
