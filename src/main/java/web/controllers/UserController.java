package web.controllers;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import web.models.User;

import javax.validation.Valid;

public interface UserController {
    String showAll(Model model);

    String show(Model model, @RequestParam(name = "id") int id);

    @GetMapping("/create")
    String createUserForm(@ModelAttribute("user") User user);

    String create(@ModelAttribute("user") @Valid User user,
                  BindingResult bindingResult);

    String updateForm(@RequestParam(name = "id") int id, Model model);

    String update(@ModelAttribute("user") @Valid User user,
                  BindingResult bindingResult);

    String delete(@RequestParam(name = "id") int id);
}
