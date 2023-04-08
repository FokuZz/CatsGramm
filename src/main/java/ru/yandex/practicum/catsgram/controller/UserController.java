package ru.yandex.practicum.catsgram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/users")
    public Collection<User> findAll() {
        return service.findAll();
    }

    @GetMapping("/users/{email}")
    public User findEmail(@PathVariable String email) {
        return service.findEmail(email);
    }

    @PostMapping(value = "/user")
    public User create(@RequestBody @Valid @NotNull User user) {
        return service.create(user);
    }

    @PutMapping("/user")
    public User put(@RequestBody @Valid @NotNull User user) {
        return service.put(user);
    }
}