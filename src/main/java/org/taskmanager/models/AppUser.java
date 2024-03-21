package org.taskmanager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taskmanager.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Password> password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Role role;
}
