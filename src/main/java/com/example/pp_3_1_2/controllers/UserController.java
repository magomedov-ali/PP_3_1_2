package com.example.pp_3_1_2.controllers;

import com.example.pp_3_1_2.models.User;
import com.example.pp_3_1_2.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/new";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "users/edit";
    }

    @PatchMapping("/update")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @RequestParam("id") int id) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }

}
