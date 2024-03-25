package org.taskmanager.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String dateOfBirth;
    private String gender;
}
