package com.gmail.petrikov05.app.controller;

import java.util.List;

import javax.validation.Valid;

import com.gmail.petrikov05.app.repository.constant.RoleEnum;
import com.gmail.petrikov05.app.service.UserService;
import com.gmail.petrikov05.app.service.model.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping
    public String showUsersPage(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
        Long pages = userService.getQuantityPage();
        model.addAttribute("pages", pages);
        model.addAttribute("page", page);
        List<UserDTO> users = userService.getAllUsers(page);
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add")
    public String showAddPage(Model model) {
        RoleEnum[] roles = userService.getAllRoles();
        model.addAttribute("roles", roles);
        return "user_add";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute(name = "user") UserDTO user) {
        userService.add(user);
        return "redirect:/home";
    }

}
