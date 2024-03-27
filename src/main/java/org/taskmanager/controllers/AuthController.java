package org.taskmanager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taskmanager.exceptions.TaskManagerException;
import org.taskmanager.models.DTOs.AppUserLogInDTO;
import org.taskmanager.models.DTOs.AppUserRegistrationDTO;
import org.taskmanager.security.AuthenticationService;
import org.taskmanager.servicies.AppUserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AppUserService appUserService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> signUpUser(@RequestBody AppUserRegistrationDTO appUserRegistrationDTO) {
        try {
            return ResponseEntity.status(201).body(appUserService.create(appUserRegistrationDTO));
        } catch (TaskManagerException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> logInUser(@RequestBody AppUserLogInDTO appUserLogInDTO) {
        try {
            return ResponseEntity.ok().body(authenticationService.authenticate(appUserLogInDTO));
        }
        catch (BadCredentialsException e){
            return ResponseEntity.status(403).body(e.getMessage());
        }
        catch (TaskManagerException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}