package com.salesflow.adapter.controller;

import com.salesflow.domain.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flow/usuarios")
public class UsuarioAdminController {

    private final UserService userService;

    public UsuarioAdminController(UserService userService) {
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
