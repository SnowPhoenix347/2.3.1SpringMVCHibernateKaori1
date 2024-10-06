package web.controllers;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.models.User;

import javax.validation.Valid;

@RequestMapping("/users")
public interface UserController {
    @GetMapping()
    String showAll(Model model);

    @GetMapping(params = "id")
    String show(Model model, @RequestParam(name = "id") int id);

    @GetMapping("/create")
    String createUserForm(@ModelAttribute("user") User user);

    @PostMapping()
    String create(@ModelAttribute("user") @Valid User user,
                  BindingResult bindingResult);

    @GetMapping("/update")
    String updateForm(@RequestParam(name = "id") int id, Model model);

    @PostMapping("/update")
    String update(@ModelAttribute("user") @Valid User user,
                  BindingResult bindingResult);

    @PostMapping("/delete")
    String delete(@RequestParam(name = "id") int id);
}
