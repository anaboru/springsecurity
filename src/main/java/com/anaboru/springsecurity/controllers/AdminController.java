package com.anaboru.springsecurity.controllers;

import com.anaboru.springsecurity.models.User;
import com.anaboru.springsecurity.services.RoleService;
import com.anaboru.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private UserService userService;

    private RoleService roleService;


    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("admin/")
    public String allUsersPage(Model model) {
        model.addAttribute("users", userService.findAll());

        return "admin/panel";
    }

    @GetMapping("/admin/{id}")
    public String specificUserPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findById(id));

        return "admin/profile";
    }

    @GetMapping("/admin/new")
    public String userCreationPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getRoles());

        return "admin/new";
    }

    @PostMapping("admin/new")
    public String userCreation(@ModelAttribute("user") User user) {
        userService.save(user);

        return "redirect:/admin/";
    }

    @DeleteMapping("admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.remove(id);

        return "redirect:/admin/";
    }

    @GetMapping("admin/{id}/edit")
    public String updateUserPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.getRoles());

        return "admin/edit";
    }

    @PatchMapping("admin/{id}/edit")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {

        userService.update(id, user);


        return "redirect:/admin/";
    }

}