package org.taskmanager.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taskmanager.enums.Gender;
import org.taskmanager.enums.Role;
import org.taskmanager.models.AppUser;
import org.taskmanager.models.DTOs.AppUserRegistrationDTO;
import org.taskmanager.models.Password;
import org.taskmanager.repositories.AppUserRepository;
import org.taskmanager.repositories.PasswordRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordRepository passwordRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, PasswordRepository passwordRepository) {
        this.appUserRepository = appUserRepository;
        this.passwordRepository = passwordRepository;
    }

    public void create(AppUserRegistrationDTO appUserDto) {
        LocalDate dateOfBirth = LocalDate.parse(appUserDto.getDateOfBirth());

        AppUser appUser = appUserRepository.save(AppUser.builder()
                .username(appUserDto.getUsername())
                .name(appUserDto.getName())
                .surname(appUserDto.getSurname())
                .email(appUserDto.getEmail())
                .phone(appUserDto.getPhone())
                .dateOfBirth(dateOfBirth)
                .createdAt(LocalDateTime.now())
                .role(Role.USER)
                .gender(Gender.valueOf(appUserDto.getGender()))
                .build());

        passwordRepository.save(Password.builder()
                        .password(appUserDto.getPassword())
                        .isActive(true)
                        .createdAt(LocalDateTime.now())
                        .user(appUser)
                .build());
    }
}
