package org.taskmanager.models.DTOs;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taskmanager.enums.Priority;
import org.taskmanager.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private UUID id;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String title;
    private String description;
    private LocalDateTime dueDate;
}
