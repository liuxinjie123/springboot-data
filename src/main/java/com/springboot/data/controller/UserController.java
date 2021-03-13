package com.springboot.data.controller;

import com.springboot.data.pojo.User;
import com.springboot.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<User> list(Pageable pageable) {
        return userService.list(pageable);
    }

    @PostMapping
    public String save(@RequestBody User user) {
        userService.save(user);
        return "SUCCESS";
    }


}
