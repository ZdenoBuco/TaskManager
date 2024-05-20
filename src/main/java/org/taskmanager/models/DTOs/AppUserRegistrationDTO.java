package org.taskmanager.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taskmanager.enums.Gender;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserRegistrationDTO {
    private String nickName;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private Gender gender;
}
