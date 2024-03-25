package org.taskmanager.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.taskmanager.models.AppUser;
import org.taskmanager.models.AuthenticationResponse;
import org.taskmanager.models.DTOs.AppUserLogInDTO;
import org.taskmanager.repositories.AppUserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final AppUserRepository appUserRepository;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AppUserLogInDTO logInDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInDTO.getEmail(), logInDTO.getPassword()));

         AppUser appUser = appUserRepository.findAppUserByEmail(logInDTO.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(appUser);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
