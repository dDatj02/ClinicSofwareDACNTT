package com.example.ClinicSoftware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ClinicSoftware.Entity.User;
import com.example.ClinicSoftware.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController 
{
    @Autowired
    private UserService userService;

    @PostMapping("/register") // http://localhost:8080/users/register (Use by register page)
    User addUser(@RequestBody User user)
    {
        return userService.saveUser(user);
    }

    @PostMapping("/addDoctor") // http://localhost:8080/users/addDoctor (Use by admin page)
    User addDoctor(@RequestBody User user)
    {
        return userService.saveUser(user);
    }

    @GetMapping("/getDoctors") // http://localhost:8080/users/getDoctors
    List<User> getDoctors()
    {
        return userService.getUsersDoctor();
    }

    @GetMapping("/{userID}")
    User getUser(@PathVariable String userID) // http://localhost:8080/users/{userID}
    {
        return userService.getUser(userID);
    }

}
