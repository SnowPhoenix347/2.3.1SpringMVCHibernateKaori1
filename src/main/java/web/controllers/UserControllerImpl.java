package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.services.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "showUsers";
    }

    @Override
    @GetMapping(params = "id")
    public String show(Model model, @RequestParam(name = "id") int id) {
        model.addAttribute("user", userService.get(id));
        return "showUser";
    }

    @Override
    @GetMapping("/create")
    public String createUserForm(@ModelAttribute("user") User user) {
        return "createUser";
    }

    @Override
    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createUser";
        } else {
            userService.create(user);
            return "redirect:/users/";
        }
    }

    @Override
    @GetMapping("/update")
    public String updateForm(@RequestParam(name = "id") int id, Model model) {
        Optional<User> user = userService.get(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "updateUser";
        } else {
            return "redirect:/users/";
        }
    }

    @Override
    @PostMapping("/update")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updateUser";
        }
        userService.update(user);
        return "redirect:/users/";
    }

    @Override
    @PostMapping("/delete")
    public String delete(@RequestParam(name = "id") int id) {
        userService.delete(id);
        return "redirect:/users/";
    }
}
