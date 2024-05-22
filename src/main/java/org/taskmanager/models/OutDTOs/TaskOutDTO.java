package org.taskmanager.models.OutDTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taskmanager.enums.Priority;
import org.taskmanager.enums.Status;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dueDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastUpdated;
    private UUID ownerId;

    public TaskOutDTO(Task task) {
        this.id = task.getId();
        this.priority = task.getPriority();
        this.status = task.getStatus();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.dueDate = task.getDueDate();
        this.createdAt = task.getCreatedAt();
        this.lastUpdated = task.getLastUpdated();
        this.ownerId = task.getOwner().getId();
    }
}
