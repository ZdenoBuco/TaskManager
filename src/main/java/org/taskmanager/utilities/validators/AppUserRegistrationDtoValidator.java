package org.taskmanager.utilities.validators;

import org.taskmanager.exceptions.TaskManagerException;
import org.taskmanager.models.DTOs.AppUserRegistrationDTO;

import java.time.LocalDate;

public class AppUserRegistrationDtoValidator {

    public static void validate(AppUserRegistrationDTO appUserDTO) {
        if (appUserDTO.getUsername() == null || appUserDTO.getUsername().isBlank()) {
            throw new TaskManagerException("Sign up request without username.", 400);
        }
        if (appUserDTO.getName() == null || appUserDTO.getName().isBlank()) {
            throw new TaskManagerException("Sign up request without name.", 400);
        }
        if (appUserDTO.getSurname() == null || appUserDTO.getSurname().isBlank()) {
            throw new TaskManagerException("Sign up request without surname.", 400);
        }
        if (appUserDTO.getDateOfBirth() == null || !appUserDTO.getDateOfBirth().matches("^\\d{4}-\\d{2}-\\d{2}$") ) {
            throw new TaskManagerException("Sign up request with invalid date of birth - invalid format.", 400);
        }
        try {
            if(LocalDate.parse(appUserDTO.getDateOfBirth()).isAfter(LocalDate.now())) {
                throw new TaskManagerException("Sign up request with invalid date of birth - must be in the past.", 400);
            }
        } catch (Exception e) {
            throw new TaskManagerException("Sign up request with invalid date of birth - date of birth in the future.", 400);
        }
//        if (appUserDTO.getPassword() == null || appUserDTO.getPassword().isBlank() || appUserDTO.getPassword().length() != 64) {
//            throw new TaskManagerException("Sign up request without password or invalid cryptographic hash function.", 400);
//        }
        if (appUserDTO.getEmail() == null || !appUserDTO.getEmail().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new TaskManagerException("Sign up request without email or invalid email.", 400);
        }
        if (appUserDTO.getPhone() == null || !appUserDTO.getPhone().matches("^(?:\\+[0-9]{1,3})?[0-9]{9,10}$")) {
            throw new TaskManagerException("Sign up request without phone or invalid phone.", 400);
        }
    }

}
