package org.lukaszse.contractorsapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.model.Order;
import org.lukaszse.contractorsapp.model.User;
import org.lukaszse.contractorsapp.model.dto.OrderDto;
import org.lukaszse.contractorsapp.model.dto.OrderViewDto;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping(Mappings.USER_ADMINISTRATION)
    public String userListView(@RequestParam(name = "pageNumber", defaultValue = "1") final int pageNumber,
                               @RequestParam(name = "pageSize", defaultValue = "5") final int pageSize,
                               final Model model) {
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

    @GetMapping(Mappings.ADD_USER)
    public String addUserView(final Model model) {
        model.addAttribute(AttributeNames.USER, new User());
        return ViewNames.ADD_USER;
    }

    @GetMapping(Mappings.EDIT_USER)
    public String editUserView(@RequestParam final String userName, final Model model) {
        var user = userService.getUser(userName);
        user.setPassword(null);
        model.addAttribute(AttributeNames.USER, user);
        return ViewNames.EDIT_USER;
    }

    @PostMapping(Mappings.ADD_USER)
    public String addUser(
            @ModelAttribute(AttributeNames.USER) @Valid final User user,
            final BindingResult bindingResult,
            final Model model) {
        if (!bindingResult.hasErrors()) {
            userService.addUser(user);
            return "redirect:/" + Mappings.USER_ADMINISTRATION;
        }
        model.addAttribute(AttributeNames.USER, user);
        return ViewNames.ADD_USER;
    }

    @PostMapping(Mappings.EDIT_USER)
    public String editUser(
            @ModelAttribute(AttributeNames.USER) @Valid final User user,
            final BindingResult bindingResult,
            final Model model) {
        if (!bindingResult.hasErrors()) {
            userService.editUser(user);
            return "redirect:/" + Mappings.USER_ADMINISTRATION;
        }
        model.addAttribute(AttributeNames.USER, user);
        return ViewNames.EDIT_USER;
    }

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
            model.addAttribute(Map.of(
                    AttributeNames.MESSAGE, "Password successfully updated!",
                    AttributeNames.PASSWORD_CHANGE, new PasswordChangeDto()));
        } else {
            model.addAttribute(AttributeNames.PASSWORD_CHANGE, passwordChangeDto);
        }
        return ViewNames.PASSWORD_CHANGE;
    }

    @GetMapping(Mappings.DELETE_USER)
    public String deleteOrder(@RequestParam final String userName) {
        userService.delete(userName);
        return "redirect:/" + Mappings.USER_ADMINISTRATION;
    }

}
