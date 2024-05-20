package org.taskmanager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taskmanager.models.OutDTOs.AuthenticationResponse;
import org.taskmanager.models.InDTOs.AppUserLogInInDTO;
import org.taskmanager.models.InDTOs.AppUserRegistrationInDTO;
import org.taskmanager.security.AuthenticationService;
import org.taskmanager.servicies.AppUserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AppUserService appUserService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> signUpUser(@RequestBody AppUserRegistrationInDTO appUserRegistrationDTO) {
            return ResponseEntity.status(201).body(appUserService.create(appUserRegistrationDTO));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> logInUser(@RequestBody AppUserLogInInDTO appUserLogInDTO) {
            return ResponseEntity.ok().body(authenticationService.authenticate(appUserLogInDTO));
    }
}