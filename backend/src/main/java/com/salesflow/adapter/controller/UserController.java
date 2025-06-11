package com.salesflow.adapter.controller;

import com.salesflow.domain.model.User;
import com.salesflow.domain.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flow/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registrar")
    public void registrar(@RequestBody User user) {
        userService.registrarUsuario(user);
    }

    @GetMapping("/{usuario}")
    public User getUser(@PathVariable String usuario) {
        return userService.findUser(usuario);
    }
}
