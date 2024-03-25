package org.taskmanager.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.taskmanager.enums.Gender;
import org.taskmanager.enums.Role;
import org.taskmanager.exceptions.TaskManagerException;
import org.taskmanager.models.AppUser;
import org.taskmanager.models.AuthenticationResponse;
import org.taskmanager.models.DTOs.AppUserRegistrationDTO;
import org.taskmanager.models.Password;
import org.taskmanager.repositories.AppUserRepository;
import org.taskmanager.repositories.PasswordRepository;
import org.taskmanager.security.AuthenticationService;
import org.taskmanager.security.JwtService;
import org.taskmanager.utilities.validators.AppUserRegistrationDtoValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordRepository passwordRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, PasswordRepository passwordRepository, PasswordEncoder passwordEncoder, AuthenticationService authenticationService, JwtService jwtService) {
        this.appUserRepository = appUserRepository;
        this.passwordRepository = passwordRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse create(AppUserRegistrationDTO appUserDto) {
        AppUserRegistrationDtoValidator.validate(appUserDto);

        if (appUserRepository.existsAppUserByEmail(appUserDto.getEmail())) {
            throw new TaskManagerException("A user with this email already exists. Please choose a different email.", 400);
        }

        LocalDate dateOfBirth = LocalDate.parse(appUserDto.getDateOfBirth());
        AppUser appUser = appUserRepository.save(AppUser.builder()
                .nickName(appUserDto.getNickName())
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
                .password(passwordEncoder.encode(appUserDto.getPassword()))
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .user(appUser)
                .build());

        String jwtToken = jwtService.generateToken(appUserRepository.findAppUserById(appUser.getId()).get());
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
