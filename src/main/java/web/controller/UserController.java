package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String giveTestPage (ModelMap model) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("user", new User());
        return "userinfo";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User user) {

        userService.add(user);

        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {

        userService.delete(id);

        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {

        model.addAttribute("user", userService.get(id));
        return "update";
    }

    @PostMapping ("/updated")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }

}
