package org.taskmanager.models.OutDTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taskmanager.enums.Priority;
import org.taskmanager.enums.Status;
import org.taskmanager.models.AppUser;
import org.taskmanager.models.Task;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskOutDTO {
    private UUID id;
    private Priority priority;
    private Status status;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime dueDate;
    private LocalDateTime lastUpdated;
    private AppUser owner;

    public TaskOutDTO(Task task) {
        this.id = task.getId();
        this.priority = task.getPriority();
        this.status = task.getStatus();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.dueDate = task.getDueDate();
        this.createdAt = task.getCreatedAt();
        this.lastUpdated = task.getLastUpdated();
        this.owner = task.getOwner();
    }
}
