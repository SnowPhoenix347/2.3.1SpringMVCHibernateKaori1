package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import web.models.User;
import web.services.UserService;
import java.util.Optional;

@Controller

public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String showAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "showUsers";
    }

    @Override
    public String show(Model model, int id) {
        model.addAttribute("user", userService.get(id));
        return "showUser";
    }

    @Override
    public String createUserForm(User user) {
        return "createUser";
    }

    @Override
    public String create(User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createUser";
        } else {
            userService.create(user);
            return "redirect:/users/";
        }
    }

    @Override
    public String updateForm(int id, Model model) {
        Optional<User> user = userService.get(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "updateUser";
        } else {
            return "redirect:/users/";
        }
    }

    @Override
    public String update(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updateUser";
        }
        userService.update(user);
        return "redirect:/users/";
    }

    @Override
    public String delete(int id) {
        userService.delete(id);
        return "redirect:/users/";
    }
}
