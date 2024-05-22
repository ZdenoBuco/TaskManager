package org.taskmanager.models.InDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthIdentityInDTO {
    private String userEmail;
    private Boolean isAdmin;

    public AuthIdentityInDTO(Authentication authentication) {
        this.userEmail = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        this.isAdmin = authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
    }
}
