package org.taskmanager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Password {
    @Id
    @GeneratedValue
    private long id;
    private String password;
    private boolean isActive;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser user;

}
