package com.example.ClinicSoftware.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor    
public class DemoController 
{
    // private final AuthenticationService authenticationService;

    @GetMapping("/test")
    public ResponseEntity<String> login()
    {
        return ResponseEntity.ok("Authentication and Authorization is succeeded");
    }

}
