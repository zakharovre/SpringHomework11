package ru.geekbrains.homework11.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.homework11.models.dtos.UserDto;
import ru.geekbrains.homework11.models.entities.User;
import ru.geekbrains.homework11.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/score/current")
    public UserDto currentUserScore(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return new UserDto(user);
    }

    @GetMapping("/score/inc")
    public UserDto incrementScore(Principal principal) {
        return userService.incrementScore(principal.getName());
    }

    @GetMapping("/score/dec")
    public UserDto decrementScore(Principal principal) {
        return userService.decrementScore(principal.getName());
    }

    @GetMapping("/admin/all")
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/admin/get/{id}")
    public UserDto getUserById(@PathVariable int id) {
        User user = userService.getUserById(id).orElseThrow(() -> new RuntimeException("No user with such ID"));
        return new UserDto(user);
    }
}
