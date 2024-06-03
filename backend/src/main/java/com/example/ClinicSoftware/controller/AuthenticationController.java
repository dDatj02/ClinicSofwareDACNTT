package com.example.ClinicSoftware.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ClinicSoftware.Service.AuthenticationService;
import com.example.ClinicSoftware.auth.AuthenticationRequest;
import com.example.ClinicSoftware.auth.AuthenticationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor    

public class AuthenticationController 
{
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest)
    {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
