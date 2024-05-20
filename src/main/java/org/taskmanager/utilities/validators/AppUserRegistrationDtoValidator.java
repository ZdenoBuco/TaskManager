package org.taskmanager.utilities.validators;

import org.taskmanager.exceptions.TaskManagerException;
import org.taskmanager.models.DTOs.AppUserRegistrationDTO;

import java.time.LocalDate;

public class AppUserRegistrationDtoValidator {

    public static void validate(AppUserRegistrationDTO appUserDTO) {
        if (appUserDTO.getNickName() == null || appUserDTO.getNickName().isBlank()) {
            throw new TaskManagerException("Sign up request without nick name.", 400);
        }
        if (appUserDTO.getName() == null || appUserDTO.getName().isBlank()) {
            throw new TaskManagerException("Sign up request without name.", 400);
        }
        if (appUserDTO.getSurname() == null || appUserDTO.getSurname().isBlank()) {
            throw new TaskManagerException("Sign up request without surname.", 400);
        }
        if (appUserDTO.getDateOfBirth() == null) {
            throw new TaskManagerException("Sign up request with invalid date of birth - invalid value.", 400);
        }
        if (appUserDTO.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new TaskManagerException("Sign up request with invalid date of birth - must be in the past.", 400);
        }
        if (appUserDTO.getEmail() == null || !appUserDTO.getEmail().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new TaskManagerException("Sign up request without email or invalid email.", 400);
        }
        if (appUserDTO.getPhone() == null || !appUserDTO.getPhone().matches("^(?:\\+[0-9]{1,3})?[0-9]{9,10}$")) {
            throw new TaskManagerException("Sign up request without phone or invalid phone.", 400);
        }
    }

}
