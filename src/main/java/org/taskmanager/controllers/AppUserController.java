package org.taskmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taskmanager.exceptions.TaskManagerException;
import org.taskmanager.models.DTOs.AppUserRegistrationDTO;
import org.taskmanager.servicies.AppUserService;

@RestController
@RequestMapping("/api")
public class AppUserController {
    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/user")
    public ResponseEntity<String> signUpUser(@RequestBody AppUserRegistrationDTO appUserRegistrationDTO) {
        try {
            appUserService.create(appUserRegistrationDTO);
            return ResponseEntity.status(201).build();
        } catch (TaskManagerException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}