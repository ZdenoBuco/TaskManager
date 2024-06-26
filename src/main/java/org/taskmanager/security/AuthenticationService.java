package org.taskmanager.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.taskmanager.enums.Role;
import org.taskmanager.models.AppUser;
import org.taskmanager.models.OutDTOs.AuthenticationOutDTO;
import org.taskmanager.models.InDTOs.AppUserLogInInDTO;
import org.taskmanager.repositories.AppUserRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final AppUserRepository appUserRepository;
    private final JwtService jwtService;

    public AuthenticationOutDTO authenticate(AppUserLogInInDTO logInDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInDTO.getEmail(), logInDTO.getPassword()));

        AppUser appUser = appUserRepository.findAppUserByEmail(logInDTO.getEmail()).orElseThrow();

        Map<String, Object> claims = new HashMap<>();
        claims.put("admin", appUser.getRole() == Role.ADMIN);

        String jwtToken = jwtService.generateToken(claims, appUser);
        return AuthenticationOutDTO.builder().token(jwtToken).build();
    }
}
